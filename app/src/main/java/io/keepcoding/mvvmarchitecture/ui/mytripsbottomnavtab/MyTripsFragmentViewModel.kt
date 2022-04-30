package io.keepcoding.mvvmarchitecture.ui.mytripsbottomnavtab

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.keepcoding.mvvmarchitecture.domain.PointOfInterestEntity
import io.keepcoding.mvvmarchitecture.domain.TourActivityEntity
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelper
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelper
import io.keepcoding.mvvmarchitecture.ui.ActivityViewModel
import io.keepcoding.mvvmarchitecture.ui.PointOfInterestViewModel
import io.keepcoding.mvvmarchitecture.ui.TripViewModel
import io.keepcoding.mvvmarchitecture.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class MyTripsFragmentViewModel(private val context: Application, private val apiHelper: ApiHelper, private val localHelper: LocalHelper) : ViewModel() {
    var tripsViewModels = MutableLiveData<Resource<List<TripViewModel?>>>()
    var snackbar = MutableLiveData<Resource<String>>()

    fun fetchTripsFromLocal() {
        viewModelScope.launch {
            try {
                tripsViewModels.postValue(Resource.loading(null))
                val tripsDatabaseCall = async { localHelper.getTrips() }
                val trips = tripsDatabaseCall.await()
                val tmpTripsViewModels = trips.map {
                    TripViewModel(it.id, it.name)
                }
                tripsViewModels.postValue(Resource.success(tmpTripsViewModels))
            } catch (e: Exception) {
                tripsViewModels.postValue(Resource.error(e.localizedMessage!!, null))
            }
        }
    }

    fun saveActivity(tripRoomId: String, activityViewModel: ActivityViewModel) {
        viewModelScope.launch {
            try {
                val activityEntity: TourActivityEntity = TourActivityEntity(
                    name = activityViewModel.name,
                    tripId = tripRoomId,
                    image = activityViewModel.image,
                    shortDescription = activityViewModel.shortDescription,
                    latitude = activityViewModel.latitude,
                    longitude = activityViewModel.longitude,
                    price = activityViewModel.price,
                    currencyCode = activityViewModel.currencyCode,
                    rating = activityViewModel.rating?.toDouble(),
                    finished = false
                )
                val saveActivityDatabaseCall = async { localHelper.saveTourActivity(activityEntity) }
                saveActivityDatabaseCall.await()
                snackbar.postValue(Resource.success("Activity saved"))
            } catch (e: Exception) {
                snackbar.postValue(Resource.error(e.localizedMessage!!, e.localizedMessage!!))
            }
        }
    }

    fun savePointOfInterest(tripRoomId: String, pointOfInterestViewModel: PointOfInterestViewModel) {
        viewModelScope.launch {
            try {
                val pointOfInterestEntity: PointOfInterestEntity = PointOfInterestEntity(
                    tripId = tripRoomId,
                    rank = pointOfInterestViewModel.rank,
                    category = pointOfInterestViewModel.category,
                    name = pointOfInterestViewModel.name,
                    visited = pointOfInterestViewModel.visited,
                    latitude = pointOfInterestViewModel.latitude,
                    longitude = pointOfInterestViewModel.longitude
                )
                val savePointOfInterestDatabaseCall = async { localHelper.savePointOfInterest(pointOfInterestEntity) }
                savePointOfInterestDatabaseCall.await()
                snackbar.postValue(Resource.success("Point of interest saved"))
            } catch (e: Exception) {
                snackbar.postValue(Resource.error(e.localizedMessage!!, e.localizedMessage!!))
            }
        }
    }
    fun getTrips() : LiveData<Resource<List<TripViewModel?>>> {
        return tripsViewModels
    }

    fun getSnackbar() : LiveData<Resource<String>> {
        return snackbar
    }
 }