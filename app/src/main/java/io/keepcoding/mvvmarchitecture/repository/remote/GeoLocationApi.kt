package io.keepcoding.mvvmarchitecture.repository.remote

import io.keepcoding.mvvmarchitecture.domain.*
import retrofit2.http.*

interface GeoLocationApi {

    @GET("direct")
    @Headers("Content-Type: application/json")
    suspend fun fetchGeoLocationFromCity(
                                         @Query("q") cityName: String,
                                         @Query("appid") apiKey: String) : GeoLocationResponse
}