package io.keepcoding.mvvmarchitecture.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelper
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelper

class PointOfInterestDetailFragmentViewModel(private val context: Application, private val apiHelper: ApiHelper, private val localHelper: LocalHelper) : ViewModel() {
    var pointOfInterestDetailViewModel: List<PointOfInterestDetailViewModel?>? = mutableListOf()

    fun fetchPointOfInterestFromServer(id: String) {

    }

    fun savePointOfInterest(pointOfInterestViewModel: PointOfInterestViewModel) {
        //TODO: Write save method in local api helper
    }
}