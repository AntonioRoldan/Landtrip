package io.keepcoding.mvvmarchitecture.repository.remote

import io.keepcoding.mvvmarchitecture.domain.RecommendedTripsResponse
import io.keepcoding.mvvmarchitecture.domain.TokenResponse
import io.keepcoding.mvvmarchitecture.domain.ToursAndActivitiesResponse
import retrofit2.http.*

// We create one for each API

interface AmadeusApi {
    //TODO: Add photos API
    @FormUrlEncoded
    @POST("security/oauth2/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun fetchToken(@Query("grant_type") grantType: String,
                        @Query("client_id") clientId: String, //Api key
                        @Query("client_secret") clientSecret: String //Api secret
                        ) : TokenResponse

    @GET("reference-data/recommended-locations")
    @Headers("Content-Type: application/json")
    suspend fun fetchRecommendedTrips(@Header("Authorization") authorization: String, @Query("cityCodes") cityCodes: String) : RecommendedTripsResponse

    @GET("shopping/activities")
    @Headers("Content-Type: application/json")
    suspend fun fetchToursAndActivities(@Header("Authorization") authorization: String, @Query("latitude") latitude: Number, @Query("longitude") longitude: Number) : ToursAndActivitiesResponse

    //TODO: Add activity id, points of interest
}