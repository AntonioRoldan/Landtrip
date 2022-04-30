package io.keepcoding.mvvmarchitecture.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.keepcoding.mvvmarchitecture.domain.TripEntity
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelper
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelper
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class AddTripFragmentViewModel(private val context: Application, private val apiHelper: ApiHelper, private val localHelper: LocalHelper) : ViewModel(){

    fun saveTrip(name: String) {
        viewModelScope.launch {
            val saveTripDatabaseCall = async { localHelper.saveTrip(TripEntity(id = UUID.randomUUID().toString(), name)) }
            saveTripDatabaseCall.await()
        }
    }

}