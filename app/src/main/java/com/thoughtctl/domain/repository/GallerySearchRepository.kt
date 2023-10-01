package com.thoughtctl.domain.repository

import com.thoughtctl.domain.model.GalleryItems
import com.thoughtctl.core.ResponseState

interface GallerySearchRepository {
    suspend fun gallerySearch(page:Int,search:String): ResponseState<List<GalleryItems>>
}
