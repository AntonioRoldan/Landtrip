package io.keepcoding.mvvmarchitecture.ui

import android.app.Application
import android.util.Log
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

class ActivityDetailFragmentViewModel(private val context: Application, private val apiHelper: ApiHelper, private val localHelper: LocalHelper) : ViewModel() {
    private var activityDetailViewModel = MutableLiveData<Resource<ActivityViewModel?>>()

    var snackbar = MutableLiveData<Resource<String>>()

    fun fetchActivityFromServer(id: String) {
        viewModelScope.launch {
            try {
                activityDetailViewModel.postValue(Resource.loading(null))
                val tokenApiCall = async {
                    apiHelper.fetchToken(
                        grantType = "client_credentials",
                        clientId = Api.AMADEUS_API_KEY,
                        clientSecret = Api.AMADEUS_API_SECRET
                    )
                }
                val accessToken: String? = tokenApiCall.await().accessToken
                accessToken?.let {
                    Log.v("ACTIVITY ID", id)
                    val activityByIdApiCall =
                        async { apiHelper.fetchActivityById(authorization = "Bearer $it", activityId = id) }
                    val activityResponse = activityByIdApiCall.await()
                    val tmpActivityViewModel: ActivityViewModel = ActivityViewModel(
                        name = activityResponse.data?.name,
                        image = activityResponse.data?.pictures?.get(0),
                        rating = activityResponse.data?.rating,
                        latitude = activityResponse.data?.geoCode?.latitude?.toDouble(),
                        longitude = activityResponse.data?.geoCode?.longitude?.toDouble(),
                        visited = false,
                        id = id,
                        shortDescription = activityResponse.data?.shortDescription,
                        price = activityResponse.data?.price?.amount,
                        currencyCode = activityResponse.data?.price?.currencyCode
                    )
                    activityDetailViewModel.postValue(Resource.success(tmpActivityViewModel))
                }
            } catch (e: Exception) {
                activityDetailViewModel.postValue(Resource.error(e.localizedMessage!!, null))
            }
        }
    }

    fun deleteActivity(id: String){
        viewModelScope.launch {
            try {
                val deleteActivityDatabaseCall = async { localHelper.deleteTourActivity(id) }
                deleteActivityDatabaseCall.await()
                snackbar.postValue(Resource.success("Activity deleted"))
            } catch (e: Exception) {
                snackbar.postValue(Resource.error(e.localizedMessage!!, null))
            }
        }
    }

    fun updateVisitedFieldOfActivityEntityFromLocal(visited: Boolean) {
        viewModelScope.launch {
            try {
                val updateVisitedFieldFromActivityEntityDatabaseCall = async { localHelper.updateTourActivity(visited) }
                updateVisitedFieldFromActivityEntityDatabaseCall.await()
                snackbar.postValue(Resource.success("Visited checked"))
            } catch (e: Exception) {
                snackbar.postValue(Resource.error(e.localizedMessage!!, null))
            }
        }
    }

    // We do not need to make a database call and fetch from local since we will pass the parcelable to this fragment as an argument

    fun getActivityDetailViewModel() : LiveData<Resource<ActivityViewModel?>> {
        return activityDetailViewModel
    }

    fun getSnackbar() : LiveData<Resource<String>> {
        return snackbar
    }
}