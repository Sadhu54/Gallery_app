package com.thoughtctl.data.source.api

import com.thoughtctl.data.source.dto.GallerySearchDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {
    // GET API for fetching list of search item
    @GET("gallery/search/top/week/{page}")
    suspend fun search(@Path ("page") page_no:Int,@Query("q") search:String): Response<GallerySearchDTO>
}