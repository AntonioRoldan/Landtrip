package io.keepcoding.mvvmarchitecture.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelper
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelper
import io.keepcoding.mvvmarchitecture.utils.Api
import io.keepcoding.mvvmarchitecture.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeFragmentViewModel(private val context: Application, private val cityCode: String, private val apiHelper: ApiHelper, private val localHelper: LocalHelper) : ViewModel() {
    // private val POJORetrofitResponses = MutableLiveData<Resource<List<POJORetrofitResponseItemViewModel?>>>() to show it in a recycler view
    private val recommendedTrips = MutableLiveData<Resource<List<RecommendedTripViewModel>>>()

    fun fetchRecommendedTrips(){
        viewModelScope.launch {
            recommendedTrips.postValue(Resource.loading(null))
            try {
                val tokenResponse = async { apiHelper.fetchToken(grantType = "client_credentials", clientId = Api.AMADEUS_API_KEY, clientSecret = Api.AMADEUS_API_SECRET)}
                val accessToken : String? = tokenResponse.await().accessToken
                accessToken?.let { token ->
                    Log.i("Access token", token)
                    val recommendedTripsResponse = async { apiHelper.fetchRecommendedTrips(authorization = token, cityCodes = cityCode) }
                    val recommendedTripsViewModels: List<RecommendedTripViewModel?>? = recommendedTripsResponse.await().data?.map { recommendedTrip ->
                        val imageUrl: String?
                        val cityName: String
                        recommendedTrip?.name?.let { cityName ->
                            val imageResponse = async { apiHelper.fetchPhotos(authorization = Api.IMAGES_API_KEY, query = recommendedTrip.name) }
                            val imageObjectsArray = imageResponse.await().results
                            imageObjectsArray?.let { imageObjects ->
                                imageUrl = imageObjects[0]?.urls?.raw
                                imageUrl?.let { image ->
                                    RecommendedTripViewModel(name = cityName, image = image)
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                recommendedTrips.postValue(Resource.error(e.localizedMessage!!, null))
            }
        }
    }

    // fun fetchPojoRetrofitResponse() {
        // viewModelScope.launch {
            // POJORetrofitResponses.postValue(Resource.loading(null))
    //         try {
    //            val response = async { apiHelper.getEvents() }
    //            val POJORetrofitResponseItemViewModels: List<POJORetrofitResponseItemViewModel?>? = response.await().POJORetrofitResponses?.map {
    //              it?.data?.let { data ->
    //                  POJORetrofitResponseItemViewModel(data = data, title = it.title, description = it.description as String?)
    //              }
    //            }
    //            POJORetrofitResponses.postValue(Resource.success(POJORetrofitResponseItemViewModels))
    //         } catch(e: Exception) {
    //              POJORetrofitResponses.postValue(Resource.error(e.localizedMessage!!, null))
    //         }
        // }
    //  }

    //
    // fun getPOJORetrofitResponses(): LiveData<Resource<List<POJORetrofitResponseItemViewModel?>>> {
    //      return POJORetrofitResponses
    // }
    //
    //
}