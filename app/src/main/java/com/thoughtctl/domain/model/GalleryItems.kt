package com.thoughtctl.domain.model

data class GalleryItems (
    val id:String,
    val title:String,
    val imageCount:Int?,
    var dateTime:String,
    val image:String?
        )