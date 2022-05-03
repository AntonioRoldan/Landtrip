package io.keepcoding.mvvmarchitecture.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.keepcoding.mvvmarchitecture.domain.TripEntity
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelper
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelper
import io.keepcoding.mvvmarchitecture.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

class AddTripFragmentViewModel(private val context: Application, private val apiHelper: ApiHelper, private val localHelper: LocalHelper) : ViewModel(){

    var snackbar = MutableLiveData<Resource<String>>()

    fun saveTrip(name: String) {
        viewModelScope.launch {
            try {
                val saveTripDatabaseCall = async { localHelper.saveTrip(TripEntity(id = UUID.randomUUID().toString(), name)) }
                saveTripDatabaseCall.await()
                snackbar.postValue(Resource.success("$name was saved"))
            } catch (e: Exception){
                snackbar.postValue(Resource.error(e.localizedMessage!!, null))
            }
        }
    }

    fun getSnackbar() : LiveData<Resource<String>> {
        return snackbar
    }

}