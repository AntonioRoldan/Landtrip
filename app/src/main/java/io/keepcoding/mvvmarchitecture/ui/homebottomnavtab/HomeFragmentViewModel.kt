package io.keepcoding.mvvmarchitecture.ui.homebottomnavtab

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelper
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelper
import io.keepcoding.mvvmarchitecture.utils.Api
import io.keepcoding.mvvmarchitecture.utils.Constants
import io.keepcoding.mvvmarchitecture.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeFragmentViewModel(private val context: Application, private val apiHelper: ApiHelper, private val localHelper: LocalHelper) : ViewModel() {
    // private val POJORetrofitResponses = MutableLiveData<Resource<List<POJORetrofitResponseItemViewModel?>>>() to show it in a recycler view
    private val recommendedTrips = MutableLiveData<Resource<List<RecommendedTripViewModel?>>>()

    fun fetchRecommendedTrips(){
        viewModelScope.launch {
            recommendedTrips.postValue(Resource.loading(null))
            try {
                val tokenResponse = async { apiHelper.fetchToken(grantType = "client_credentials", clientId = Api.AMADEUS_API_KEY, clientSecret = Api.AMADEUS_API_SECRET)}
                val accessToken : String? = tokenResponse.await().accessToken
                accessToken?.let { token ->
                    Log.i("Access token", token)
                    val recommendedTripsResponse = async { apiHelper.fetchRecommendedTrips(authorization = token, cityCodes = Constants.CITY_CODE) }
                    val recommendedTripsViewModels: List<RecommendedTripViewModel?>? = recommendedTripsResponse.await().data?.map { recommendedTrip ->
                        val imageUrl: String?
                        recommendedTrip?.let { tripResponseObject ->
                            tripResponseObject.name?.let { cityName ->
                                val imageResponse = async { apiHelper.fetchPhotos(authorization = Api.IMAGES_API_KEY, query = cityName) }
                                val imageObjectsArray = imageResponse.await().results
                                imageObjectsArray?.let { imageObjects ->
                                    imageUrl = imageObjects[0]?.urls?.raw
                                    imageUrl?.let { image ->
                                        tripResponseObject.geoCode?.latitude?.let { latitude ->
                                            tripResponseObject.geoCode.longitude?.let { longitude ->
                                                RecommendedTripViewModel(name = cityName, image = image, latitude = latitude, longitude = longitude)
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                    recommendedTrips.postValue(Resource.success(recommendedTripsViewModels))
                }
            } catch (e: Exception) {
                recommendedTrips.postValue(Resource.error(e.localizedMessage!!, null))
            }
        }
    }

    fun getRecommendedTrips() : LiveData<Resource<List<RecommendedTripViewModel?>>> {
        return recommendedTrips
    }

}