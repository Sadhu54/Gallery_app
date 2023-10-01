package com.thoughtctl.presentation.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thoughtctl.core.ResponseState
import com.thoughtctl.domain.model.GalleryItems
import com.thoughtctl.domain.use_cae.GallerySearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val useCase:GallerySearchUseCase): ViewModel() {

    private val _gallerySearchList= MutableStateFlow<ResponseState<List<GalleryItems>>?>(null)
    val gallerySearchList:StateFlow<ResponseState<List<GalleryItems>>?> = _gallerySearchList


    // function to search gallery and return the data in state flow
    fun searchGallery(search:String){
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getGallerySearch(search).collect{
                _gallerySearchList.value=it
           }
        }
    }
}