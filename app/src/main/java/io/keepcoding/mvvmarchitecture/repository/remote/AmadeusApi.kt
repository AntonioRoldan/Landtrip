package io.keepcoding.mvvmarchitecture.repository.remote

import io.keepcoding.mvvmarchitecture.domain.*
import retrofit2.http.*

// We create one for each API

interface AmadeusApi {
    //TODO: Add photos API, and here api (to get geolocation from city name)
    @FormUrlEncoded
    @POST("security/oauth2/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun fetchToken(@Field("grant_type") grantType: String, // Set to client_credentials
                           @Field("client_id") clientId: String, //Api key
                           @Field("client_secret") clientSecret: String //Api secret
                        ) : TokenResponse

    @GET("reference-data/recommended-locations")
    @Headers("Content-Type: application/json")
    suspend fun fetchRecommendedTrips(@Header("Authorization") authorization: String, // Access token
                                      @Query("cityCodes") cityCodes: String) : RecommendedTripsResponse

    @GET("shopping/activities")
    @Headers("Content-Type: application/json")
    suspend fun fetchToursAndActivities(@Header("Authorization") authorization: String,
                                        @Query("latitude") latitude: Double,
                                        @Query("longitude") longitude: Double) : ToursAndActivitiesResponse

    @GET("shopping/activities")
    @Headers("Content-Type: application/json")
    suspend fun fetchActivityById(@Header("Authorization") authorization: String,
                                  @Path("activityId") activityId: String) : ToursAndActivitiesByIdResponse


    @GET("reference-data/locations/pois")
    @Headers("Content-Type: application/json")
    suspend fun fetchPointsOfInterest(@Header("Authorization") authorization: String,
                                      @Query("latitude") latitude: Double,
                                      @Query("longitude") longitude: Double) : PointsOfInterestResponse//TODO: Check if the example from api reference was right

    @GET("reference-data/locations/pois")
    @Headers("Content-Type: application/json")
    suspend fun fetchPointOfInterestById(@Header("Authorization") authorization: String,
                                        @Path("poisId") pointOfInterestId: String) : PointOfInterestByIdResponse
}

