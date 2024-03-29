package io.keepcoding.mvvmarchitecture.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelper
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelperImpl
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelper
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelperImpl
import android.content.pm.PackageManager
import android.util.Log
import io.keepcoding.mvvmarchitecture.ui.ActivitiesAndPointsOfInterestFragmentViewModel
import io.keepcoding.mvvmarchitecture.ui.ActivityDetailFragmentViewModel
import io.keepcoding.mvvmarchitecture.ui.AddTripFragmentViewModel
import io.keepcoding.mvvmarchitecture.ui.PointOfInterestDetailFragmentViewModel
import io.keepcoding.mvvmarchitecture.ui.homebottomnavtab.HomeFragmentViewModel
import io.keepcoding.mvvmarchitecture.ui.mytripsbottomnavtab.MyTripsFragmentViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class CustomViewModelFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
    private val apiHelper: ApiHelper = ApiHelperImpl()
    private val localHelper: LocalHelper = LocalHelperImpl(context = application.applicationContext)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(HomeFragmentViewModel::class.java) -> HomeFragmentViewModel (
                    application,
                    apiHelper,
                    localHelper
                )
                isAssignableFrom(ActivitiesAndPointsOfInterestFragmentViewModel::class.java) -> ActivitiesAndPointsOfInterestFragmentViewModel(
                    application,
                    apiHelper,
                    localHelper)
                isAssignableFrom(ActivityDetailFragmentViewModel::class.java) -> ActivityDetailFragmentViewModel(
                    application,
                    apiHelper,
                    localHelper
                )
                isAssignableFrom(PointOfInterestDetailFragmentViewModel::class.java) -> PointOfInterestDetailFragmentViewModel(
                    application,
                    apiHelper,
                    localHelper
                )
                isAssignableFrom(AddTripFragmentViewModel::class.java) -> AddTripFragmentViewModel(
                    application,
                    apiHelper,
                    localHelper
                )
                isAssignableFrom(MyTripsFragmentViewModel::class.java) -> MyTripsFragmentViewModel(
                    application,
                    apiHelper,
                    localHelper
                )
                else -> throw IllegalArgumentException("Unknown ViewModel")
            }
        } as T
    }
}

