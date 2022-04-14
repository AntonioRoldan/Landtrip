package io.keepcoding.mvvmarchitecture.repository.remote

import io.keepcoding.mvvmarchitecture.domain.*
import retrofit2.http.*

// We create one for each API

interface AmadeusApi {
    //TODO: Add photos API, and here api (to get geolocation from city name)
    @FormUrlEncoded
    @POST("security/oauth2/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun fetchToken(@Query("grant_type") grantType: String,
                        @Query("client_id") clientId: String, //Api key
                        @Query("client_secret") clientSecret: String //Api secret
                        ) : TokenResponse

    @GET("reference-data/recommended-locations")
    @Headers("Content-Type: application/json")
    suspend fun fetchRecommendedTrips(@Header("Authorization") authorization: String,
                                      @Query("cityCodes") cityCodes: String) : RecommendedTripsResponse

    @GET("shopping/activities")
    @Headers("Content-Type: application/json")
    suspend fun fetchToursAndActivities(@Header("Authorization") authorization: String,
                                        @Query("latitude") latitude: Number,
                                        @Query("longitude") longitude: Number) : ToursAndActivitiesResponse

    @GET("reference-data/locations/pois")
    @Headers("Content-Type: application/json")
    suspend fun fetchPointsOfInterest(@Header("Authorization") authorization: String,
                                      @Query("latitude") latitude: Number,
                                      @Query("longitude") longitude: Number) : PointsOfInterestResponse//TODO: Check if the example from api reference was right

    @GET("shopping/activities")
    @Headers("Content-Type: application/json")
    suspend fun fetchActivityById(@Header("Authorization") authorization: String,
                                  @Path("activityId") activityId: String) : ToursAndActivitiesByIdResponse

}