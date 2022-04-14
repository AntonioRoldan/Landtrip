package io.keepcoding.mvvmarchitecture.repository.remote

import io.keepcoding.mvvmarchitecture.domain.*
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Query

// import io.keepcoding.mvvmarchitecture.POJORetrofitResponse
// We can also use it to combine multiple apis
interface ApiHelper { //We can mix apis here, these are the retrofit methods we defined in the api interface being developed
    // TODO: Write
    // suspend fun getRetrofitResponse(q: String) : POJORetrofitResponse

    suspend fun fetchToken(grantType: String, clientId: String, clientSecret: String) : TokenResponse
    // We will fetch a token every time we make an api call
    suspend fun fetchRecommendedTrips(authorization: String, cityCodes: String) : RecommendedTripsResponse

    suspend fun fetchToursAndActivities(authorization: String, latitude: Number, longitude: Number) : ToursAndActivitiesResponse

    suspend fun fetchActivityById(authorization: String, activityId: String) : ToursAndActivitiesByIdResponse

    suspend fun fetchPointsOfInterest(authorization: String, latitude: Number, longitude: Number) : PointsOfInterestResponse

    suspend fun fetchPhotos(authorization: String, query: String) : ImagesResponse

    suspend fun fetchGeoLocationFromCity(cityName: String, apiKey: String) : GeoLocationResponse

}