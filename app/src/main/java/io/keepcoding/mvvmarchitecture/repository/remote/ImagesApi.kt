package io.keepcoding.mvvmarchitecture.repository.remote

import io.keepcoding.mvvmarchitecture.domain.*
import retrofit2.http.*


interface ImagesApi {
    @GET("search/photos")
    @Headers("Content-Type: application/json")
    suspend fun fetchPhotos(@Header("Authorization") authorization: String,
                            @Query("query") query: String) : ImagesResponse
}