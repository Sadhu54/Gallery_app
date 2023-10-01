package com.thoughtctl.data.repository

import com.thoughtctl.core.BaseRepo
import com.thoughtctl.core.ResponseState
import com.thoughtctl.core.mapSuccess
import com.thoughtctl.data.source.api.SearchApi
import com.thoughtctl.domain.model.GalleryItems
import com.thoughtctl.domain.repository.GallerySearchRepository
import javax.inject.Inject

class GallerySearchRepositoryImpl @Inject constructor(private val searchApi: SearchApi):BaseRepo(), GallerySearchRepository {
    override suspend fun gallerySearch(
        page: Int,
        search: String
    ): ResponseState<List<GalleryItems>> {
        return safeApiCall {
            searchApi.search(page,search)
        }.mapSuccess {dto->
            // map successful response to list of gallery item
            dto.toGalleryItem()
        }
    }

}