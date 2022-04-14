package io.keepcoding.mvvmarchitecture.repository.remote

import io.keepcoding.mvvmarchitecture.domain.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ApiHelperImpl() : ApiHelper {

    private val amadeusService: AmadeusApi = RemoteDataManager().amadeusApi
    private val imagesService: ImagesApi = RemoteDataManager().imagesApi
    private val geoLocationService: GeoLocationApi = RemoteDataManager().geoLocationApi

    override suspend fun fetchToken(
        grantType: String,
        clientId: String,
        clientSecret: String
    ): TokenResponse {
        var response: TokenResponse
        withContext(Dispatchers.IO) {
            response = amadeusService.fetchToken(grantType, clientId, clientSecret)
        }
        return response
    }

    override suspend fun fetchRecommendedTrips(
        authorization: String,
        cityCodes: String
    ): RecommendedTripsResponse {
        var response: RecommendedTripsResponse
        withContext(Dispatchers.IO) {
            response = amadeusService.fetchRecommendedTrips(authorization, cityCodes)
        }
        return response    }

    override suspend fun fetchToursAndActivities(
        authorization: String,
        latitude: Number,
        longitude: Number
    ): ToursAndActivitiesResponse {
        var response: ToursAndActivitiesResponse
        withContext(Dispatchers.IO) {
            response = amadeusService.fetchToursAndActivities(authorization, latitude, longitude)
        }
        return response    }

    override suspend fun fetchActivityById(
        authorization: String,
        activityId: String
    ): ToursAndActivitiesByIdResponse {
        var response: ToursAndActivitiesByIdResponse
        withContext(Dispatchers.IO) {
            response = amadeusService.fetchActivityById(authorization, activityId)
        }
        return response    }

    override suspend fun fetchPointsOfInterest(
        authorization: String,
        latitude: Number,
        longitude: Number
    ): PointsOfInterestResponse {
        var response: PointsOfInterestResponse
        withContext(Dispatchers.IO) {
            response = amadeusService.fetchPointsOfInterest(authorization, latitude, longitude)
        }
        return response    }

    override suspend fun fetchPhotos(authorization: String, query: String): ImagesResponse {
        var response: ImagesResponse
        withContext(Dispatchers.IO) {
            response = imagesService.fetchPhotos(authorization, query)
        }
        return response    }

    override suspend fun fetchGeoLocationFromCity(
        cityName: String,
        apiKey: String
    ): GeoLocationResponse {
        var response: GeoLocationResponse
        withContext(Dispatchers.IO) {
            response = geoLocationService.fetchGeoLocationFromCity(cityName, apiKey)
        }
        return response
    }
}