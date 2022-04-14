package io.keepcoding.mvvmarchitecture.repository.remote

import io.keepcoding.mvvmarchitecture.domain.*
import retrofit2.http.*

interface GeoLocationApi {

    @GET("geocode")
    @Headers("Content-Type: application/json")
    suspend fun fetchGeoLocationFromCity(@Header("Authorization") authorization: String,
                                         @Query("q") cityName: String,
                                         @Query("apiKey") apiKey: String) : GeoLocationResponse

}