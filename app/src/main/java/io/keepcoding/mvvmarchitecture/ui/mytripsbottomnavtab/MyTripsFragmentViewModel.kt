package io.keepcoding.mvvmarchitecture.ui.mytripsbottomnavtab

import android.app.Application
import android.util.Log
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
import java.util.*

class MyTripsFragmentViewModel(private val context: Application, private val apiHelper: ApiHelper, private val localHelper: LocalHelper) : ViewModel() {
    var tripsViewModels = MutableLiveData<Resource<List<TripViewModel?>>>()

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
                //TODO: Add a snackbar to notify user of the status of the app
            } catch (e: Exception) {
                Log.e("Save activity error", e.localizedMessage!!)
            }
        }
    }
    fun savePointOfInterest(tripRoomId: String, pointOfInterestViewModel: PointOfInterestViewModel){
        try {
            //val pointOfInterestEntity: PointOfInterestEntity = PointOfInterestEntity(
            //                tripId = tripRoomId,
            //                latitude = pointOfInterestViewModel.latitude,
            //                longitude = pointOfInterestViewModel.longitude,
            //                rank = pointOfInterestViewModel.rank,
            //
            //            )

        } catch (e: Exception) {
            Log.e("Save point of interest", e.localizedMessage!!)
        }
    }
}