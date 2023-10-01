package com.thoughtctl.data.source.dto

import com.thoughtctl.domain.model.GalleryItems
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class GallerySearchDTO(
    val `data`: List<Data>,
    val status: Int,
    val success: Boolean
){
    fun toGalleryItem():List<GalleryItems>{
        return data.map {
            GalleryItems(
                id= it.id,
                title = it.title,
                image = it.images?.get(0)?.link,
                imageCount = it.images?.size,
                dateTime = convertMillisecondsToDateTime(it.datetime.toLong())
            )
        }
    }

    fun convertMillisecondsToDateTime(milliseconds: Long): String {
        val dateFormat = SimpleDateFormat("dd/MMM/yyyy hh:mm a", Locale.getDefault())
        val calendar = Calendar.getInstance()
        milliseconds.also { calendar.timeInMillis = it }
        return dateFormat.format(calendar.time)
    }
}