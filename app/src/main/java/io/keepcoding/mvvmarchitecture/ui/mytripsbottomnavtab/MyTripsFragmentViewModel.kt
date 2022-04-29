package io.keepcoding.mvvmarchitecture.ui.mytripsbottomnavtab

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.keepcoding.mvvmarchitecture.domain.TourActivityEntity
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelper
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelper
import io.keepcoding.mvvmarchitecture.ui.ActivityViewModel
import io.keepcoding.mvvmarchitecture.ui.TripViewModel
import kotlinx.coroutines.launch
import java.util.*

class MyTripsFragmentViewModel(private val context: Application, private val apiHelper: ApiHelper, private val localHelper: LocalHelper) : ViewModel() {
    var tripViewModel: List<TripViewModel?>? = mutableListOf()

    fun saveActivity(tripRoomId: String, activityViewModel: ActivityViewModel) {
        viewModelScope.launch {
            activityViewModel.name?.let{ name ->
                //val activityEntity = TourActivityEntity(
                //                    id = UUID.randomUUID().toString(),
                //                    tripId = tripRoomId,
                //                    name = name,
                //
                //                )
                //val saveActivityOperation = localHelper.saveTourActivity()
            }
        }
    }
}