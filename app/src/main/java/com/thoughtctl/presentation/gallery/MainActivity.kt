package com.thoughtctl.presentation.gallery

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.angads25.toggle.interfaces.OnToggledListener
import com.github.angads25.toggle.model.ToggleableView
import com.github.angads25.toggle.widget.LabeledSwitch
import com.thoughtctl.core.ResponseState
import com.thoughtctl.databinding.ActivityMainBinding
import com.thoughtctl.presentation.gallery.adapter.GalleryAdapter
import com.thoughtctl.utils.DelayedTextWatcher
import com.thoughtctl.utils.GridSpacingItemDecoration
import com.thoughtctl.utils.LayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<GalleryViewModel>()
    var layoutManagerConst=LayoutManager.LIST.value
    var isItemDecorationAdded=false
    private val galleryAdapter by lazy { GalleryAdapter() }
    lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding= ActivityMainBinding.inflate(layoutInflater)
        // Create an instance of DelayedTextWatcher
        val textWatcher = DelayedTextWatcher({ searchText ->
            // This code will be executed when the user stops typing
            // Perform your search operation here using searchText
            viewModel.searchGallery(searchText)
        })
        mBinding.run {
            etGallerySearch.addTextChangedListener(textWatcher)
            toolbarGallery.switchLayoutManager.setOnToggledListener(object : OnToggledListener {
                override fun onSwitched(toggleableView: ToggleableView?, isOn: Boolean) {
                    layoutManagerConst = when(isOn) {
                        true->{
                            LayoutManager.LIST.value
                        }
                        false->{
                            LayoutManager.GRID.value
                        }
                    }
                    setUpGallery()
                }
            })
        }
        setUpGallery()
        setObserver()
        setContentView(mBinding.root)
    }

    // function to set recycler
    private fun setUpGallery()
    {
        mBinding.rvGallery.run {
            when(layoutManagerConst)
            {
                LayoutManager.GRID.value->{
                    layoutManager=GridLayoutManager(context,2)
                    val spacingPx = 20 // Define your desired spacing in resources
                    val spanCount = 2 // Set your desired number of columns

                    if (isItemDecorationAdded) {
                        // Remove the existing GridSpacingItemDecoration if it was previously added
                        val existingItemDecoration = getItemDecorationAt(0)
                        if (existingItemDecoration is GridSpacingItemDecoration) {
                            removeItemDecoration(existingItemDecoration)
                        }
                    }

                    // Create the new GridSpacingItemDecoration
                    val itemDecoration = GridSpacingItemDecoration(spanCount, spacingPx)
                    // Attach the item decoration to your RecyclerView
                    addItemDecoration(itemDecoration)
                    isItemDecorationAdded = true // Update the flag
                }
                LayoutManager.LIST.value->{
                    layoutManager=LinearLayoutManager(context)
                }
            }
            adapter=galleryAdapter
        }
    }

    // function to observe state
    private fun setObserver()
    {
        mBinding.run {
            lifecycleScope.launch {
                viewModel.gallerySearchList.collectLatest { it ->
                    it?.let {state->
                        when(state)
                        {
                            is ResponseState.Success->{
                                lottieLoader.visibility= View.GONE
                                state.data?.let {searchList->
                                    tvError.isVisible=searchList.isEmpty()
                                    rvGallery.isVisible=searchList.isNotEmpty()
                                    if (searchList.isNotEmpty())
                                    {
                                        searchList.sortedByDescending {item->
                                            SimpleDateFormat("dd/MMM/yyyy hh:mm a", Locale.getDefault()).parse(item.dateTime)?.time
                                        }
                                        galleryAdapter.submitData(ArrayList(searchList))
                                    }
                                }
                            }

                            is ResponseState.Error->{
                                lottieLoader.visibility= View.GONE
                                rvGallery.visibility=View.VISIBLE
                            }

                            is ResponseState.Loading->{
                                lottieLoader.visibility= View.VISIBLE
                                rvGallery.visibility=View.GONE
                            }
                        }

                    }

                }
            }
        }
    }
}