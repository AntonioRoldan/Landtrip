package io.keepcoding.mvvmarchitecture.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelper
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelper
import io.keepcoding.mvvmarchitecture.utils.Api
import io.keepcoding.mvvmarchitecture.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PointOfInterestDetailFragmentViewModel(private val context: Application, private val apiHelper: ApiHelper, private val localHelper: LocalHelper) : ViewModel() {
    var pointOfInterestDetailViewModel = MutableLiveData<Resource<PointOfInterestViewModel?>>()

    fun fetchPointOfInterestFromServer(id: String) {
        viewModelScope.launch {
            try {
                pointOfInterestDetailViewModel.postValue(Resource.loading(null))
                val tokenApiCall = async {
                    apiHelper.fetchToken(
                        grantType = "client_credentials",
                        clientId = Api.AMADEUS_API_KEY,
                        clientSecret = Api.AMADEUS_API_SECRET
                    )
                }
                val accessToken: String? = tokenApiCall.await().accessToken
                accessToken?.let {
                    val pointOfInterestByIdApiCall = async { apiHelper.fetchPointOfInterestById(authorization = it, pointOfInterestId = id) }
                    val pointOfInterestResponse = pointOfInterestByIdApiCall.await()
                    val tmpPointOfInterestDetailViewModel: PointOfInterestViewModel = PointOfInterestViewModel(
                        name = pointOfInterestResponse.data?.name,
                        category = pointOfInterestResponse.data?.category,
                        rank = pointOfInterestResponse.data?.rank,
                        latitude = pointOfInterestResponse.data?.geoCode?.latitude,
                        longitude = pointOfInterestResponse.data?.geoCode?.longitude,
                        visited = false,
                        id = id
                    )
                    pointOfInterestDetailViewModel.postValue(Resource.success(tmpPointOfInterestDetailViewModel))
                }
            } catch (e: Exception){
                pointOfInterestDetailViewModel.postValue(Resource.error(e.localizedMessage!!, null))
            }
        }
    }

    // We do not need to make a database call and fetch from local since we will pass the parcelable to this fragment as an argument

    fun getPointOfInterestDetailViewModel() : LiveData<Resource<PointOfInterestViewModel?>> {
        return pointOfInterestDetailViewModel
    }
}