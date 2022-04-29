package io.keepcoding.mvvmarchitecture.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.keepcoding.mvvmarchitecture.domain.TripEntity
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelper
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelper
import io.keepcoding.mvvmarchitecture.ui.homebottomnavtab.RecommendedTripViewModel
import io.keepcoding.mvvmarchitecture.utils.Api
import io.keepcoding.mvvmarchitecture.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class ActivitiesAndPointsOfInterestFragmentViewModel(private val context: Application, private val apiHelper: ApiHelper, private val localHelper: LocalHelper) : ViewModel() {
    private val activitiesAndPointsOfInterest = MutableLiveData<Resource<List<ActivitiesAndPointOfInterestItemInterface?>>>()

    fun fetchActivitiesAndPointsOfInterestFromServer(cityName: String) {
        viewModelScope.launch {
            try {
                activitiesAndPointsOfInterest.postValue(Resource.loading(null))
                val latitude: Double?
                val longitude: Double?
                val cityGeolocationApiCall = async {apiHelper.fetchGeoLocationFromCity(cityName, Api.GEOLOCATION_API_KEY) }
                val citiesGSONList = cityGeolocationApiCall.await()
                if(citiesGSONList.isEmpty()){
                    activitiesAndPointsOfInterest.postValue(Resource.error("No cities associated with your typed in value", null))
                } else {
                    val cityResponse = citiesGSONList[0]
                    latitude = cityResponse.lat
                    longitude = cityResponse.lon
                    val tokenApiCall = async {
                        apiHelper.fetchToken(
                            grantType = "client_credentials",
                            clientId = Api.AMADEUS_API_KEY,
                            clientSecret = Api.AMADEUS_API_SECRET
                        )
                    }
                    val accessToken: String? = tokenApiCall.await().accessToken
                    accessToken?.let {
                        latitude?.let { lat ->
                            longitude?.let { long ->
                                val pointsOfInterestApiCall = async {
                                    apiHelper.fetchPointsOfInterest(
                                        authorization = "Bearer $accessToken",
                                        latitude = lat,
                                        longitude = long
                                    )
                                }
                                val activitiesApiCall = async {
                                    apiHelper.fetchToursAndActivities(
                                        authorization = "Bearer $accessToken",
                                        latitude = lat,
                                        longitude = long
                                    )
                                }
                                val pointsOfInterestViewModels: List<ActivitiesAndPointOfInterestItemInterface>? =
                                    pointsOfInterestApiCall.await().data?.map { pointOfInterestDataItem ->
                                        PointOfInterestViewModel(
                                            id = pointOfInterestDataItem?.id,
                                            name = pointOfInterestDataItem?.name,
                                            rank = pointOfInterestDataItem?.rank,
                                            category = pointOfInterestDataItem?.category,
                                            latitude = pointOfInterestDataItem?.geoCode?.latitude,
                                            longitude = pointOfInterestDataItem?.geoCode?.longitude
                                        )
                                    }
                                val activitiesViewModels: List<ActivitiesAndPointOfInterestItemInterface>? =
                                    activitiesApiCall.await().data?.map { activityDataItem ->
                                        ActivityViewModel(
                                            id = activityDataItem?.id,
                                            name = activityDataItem?.name,
                                            currencyCode = activityDataItem?.price?.currencyCode,
                                            price = activityDataItem?.price?.amount,
                                            rating = activityDataItem?.rating,
                                            image = activityDataItem?.pictures?.get(0),
                                            shortDescription = activityDataItem?.shortDescription,
                                            latitude = activityDataItem?.geoCode?.latitude?.toDouble(),
                                            longitude = activityDataItem?.geoCode?.longitude?.toDouble()
                                        )
                                    }
                                val activitiesAndPointsOfInterestViewModels: MutableList<ActivitiesAndPointOfInterestItemInterface?> =
                                    mutableListOf()
                                activitiesViewModels?.forEach {
                                    activitiesAndPointsOfInterestViewModels.add(it)
                                }
                                pointsOfInterestViewModels?.forEach {
                                    activitiesAndPointsOfInterestViewModels.add(it)
                                }
                                activitiesAndPointsOfInterest.postValue(
                                    Resource.success(
                                        activitiesAndPointsOfInterestViewModels
                                    )
                                )
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                activitiesAndPointsOfInterest.postValue(Resource.error(e.localizedMessage!!, null))
            }
        }
    }

    fun fetchActivitiesAndPointsOfInterestFromLocal(tripId: String){
        viewModelScope.launch {
            try {
                activitiesAndPointsOfInterest.postValue(Resource.loading(null))
                val tripDatabaseCall = async {localHelper.getTripWithToursActivitiesAndPointsOfInterest(tripId) }
                val tripWithActivitiesAndPointsOfInterest: TripEntity = tripDatabaseCall.await()
                val activitiesAndPointsOfInterestViewModels: MutableList<ActivitiesAndPointOfInterestItemInterface?> = mutableListOf(null)
                val pointsOfInterestViewModels = tripWithActivitiesAndPointsOfInterest.pointsOfInterest.map {
                    PointOfInterestViewModel(name = it.name, category = it.category, rank = it.rank, latitude = it.latitude, longitude = it.longitude, visited = it.visited)
                }
                val activitiesViewModels = tripWithActivitiesAndPointsOfInterest.toursAndActivities.map {
                    ActivityViewModel(name = it.name, rating = it.rating.toString(), price = "", currencyCode = "", image = it.image, shortDescription = it.shortDescription)
                }
                activitiesViewModels.forEach {
                    activitiesAndPointsOfInterestViewModels.add(it)
                }
                pointsOfInterestViewModels.forEach {
                    activitiesAndPointsOfInterestViewModels.add(it)
                }
                activitiesAndPointsOfInterest.postValue(Resource.success(activitiesAndPointsOfInterestViewModels))
            } catch (e: Exception) {
                activitiesAndPointsOfInterest.postValue(Resource.error(e.localizedMessage!!, null))
            }
        }
    }

    fun getActivitiesAndPointsOfInterest(): LiveData<Resource<List<ActivitiesAndPointOfInterestItemInterface?>>> {
        return activitiesAndPointsOfInterest
    }

}


