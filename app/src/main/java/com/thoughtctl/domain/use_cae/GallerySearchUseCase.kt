package com.thoughtctl.domain.use_cae

import com.thoughtctl.domain.repository.GallerySearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GallerySearchUseCase @Inject constructor(private val searchRepository: GallerySearchRepository) {

    // define function to perform search and pass data as a flow
    suspend fun  getGallerySearch(search:String) = flow{
        emit(searchRepository.gallerySearch(1,search))
    }
}