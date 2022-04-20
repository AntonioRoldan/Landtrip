package io.keepcoding.mvvmarchitecture.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelper
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelperImpl
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelper
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelperImpl
import android.content.pm.PackageManager
import io.keepcoding.mvvmarchitecture.ui.HomeFragmentViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class CustomViewModelFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
    private val apiHelper: ApiHelper = ApiHelperImpl()
    private val localHelper: LocalHelper = LocalHelperImpl(context = application.applicationContext)

    init {
        this.setConstants()
    }

    private fun setConstants() {
        application.applicationContext.packageManager.getApplicationInfo(
            application.applicationContext.packageName,
            PackageManager.GET_META_DATA
        ).metaData.getString("AMADEUS_API_KEY")?.let { key ->
            Constants.AMADEUS_API_KEY = key
        }
        application.applicationContext.packageManager.getApplicationInfo(
            application.applicationContext.packageName,
            PackageManager.GET_META_DATA
        ).metaData.getString("AMADEUS_API_SECRET")?.let { secret ->
            Constants.AMADEUS_API_SECRET = secret
        }
        application.applicationContext.packageManager.getApplicationInfo(
            application.applicationContext.packageName,
            PackageManager.GET_META_DATA
        ).metaData.getString("IMAGES_API_KEY")?.let { key ->
            Constants.IMAGES_API_KEY = key
        }
        application.applicationContext.packageManager.getApplicationInfo(
            application.applicationContext.packageName,
            PackageManager.GET_META_DATA
        ).metaData.getString("IMAGES_API_SECRET")?.let { secret ->
            Constants.IMAGES_API_SECRET = secret
        }
        application.applicationContext.packageManager.getApplicationInfo(
            application.applicationContext.packageName,
            PackageManager.GET_META_DATA
        ).metaData.getString("GEOLOCATION_API_KEY")?.let { key ->
            Constants.GEOLOCATION_API_KEY = key
        }
        application.applicationContext.packageManager.getApplicationInfo(
            application.applicationContext.packageName,
            PackageManager.GET_META_DATA
        ).metaData.getString("GEOLOCATION_API_SECRET")?.let { secret ->
            Constants.GEOLOCATION_API_SECRET = secret
        }
    }
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(HomeFragmentViewModel::class.java) -> HomeFragmentViewModel (
                    application,
                    apiHelper,
                    localHelper
                )
                else -> throw IllegalArgumentException("Unknown ViewModel")
            }
        } as T
    }
}

