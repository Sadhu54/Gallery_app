package com.thoughtctl.presentation.gallery.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.thoughtctl.R
import com.thoughtctl.databinding.ItemGalleryBinding
import com.thoughtctl.databinding.ItemLoadingStateBinding
import com.thoughtctl.domain.model.GalleryItems
import java.lang.IllegalArgumentException

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryListViewHolder>() {

    // The list of gallery items to be displayed
    private var items = ArrayList<GalleryItems>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryListViewHolder {
        return GalleryListViewHolder(
            ItemGalleryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GalleryListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    // Update the adapter's data with a new list of gallery items
    fun submitData(list: ArrayList<GalleryItems>) {
        items = list
        notifyDataSetChanged()
    }

    inner class GalleryListViewHolder(private val binding: ItemGalleryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        // Bind data to the GalleryListViewHolder
        fun bind(item: GalleryItems) {
            binding.run {
                // Load the image using Picasso or other image-loading library
                ivItemImage.run {
                    Picasso.get().load(item.image).error(R.drawable.baseline_image_not_supported_24).into(this)
                }
                // Set the date, image count, and title from the GalleryItems object
                tvDateGallery.text = item.dateTime
                tvNumberImagesGallery.text = item.imageCount.toString()
                tvImageTitle.text = item.title
            }
        }
    }
}
