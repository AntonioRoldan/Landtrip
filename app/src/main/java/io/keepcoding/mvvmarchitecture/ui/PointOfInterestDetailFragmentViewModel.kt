package io.keepcoding.mvvmarchitecture.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelper
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelper
import io.keepcoding.mvvmarchitecture.utils.Resource

class PointOfInterestDetailFragmentViewModel(private val context: Application, private val apiHelper: ApiHelper, private val localHelper: LocalHelper) : ViewModel() {
    var pointOfInterestDetailViewModel = MutableLiveData<Resource<List<PointOfInterestViewModel?>>>()

    fun fetchPointOfInterestFromServer(id: String) {

    }

    fun savePointOfInterest(pointOfInterestViewModel: PointOfInterestViewModel) {
        //TODO: Write save method in local api helper
    }
}