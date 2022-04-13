package io.keepcoding.mvvmarchitecture.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelper
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelperImpl
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelper
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelperImpl
import io.keepcoding.mvvmarchitecture.ui.FragmentOrActivityViewModel
import io.keepcoding.mvvmarchitecture.utils.ApiKey
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class CustomViewModelFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
    private val apiHelper: ApiHelper = ApiHelperImpl()
    private val localHelper: LocalHelper = LocalHelperImpl(context = application.applicationContext)
    init {
        application.applicationContext.packageManager.getApplicationInfo(application.applicationContext.packageName, PackageManager.GET_META_DATA).metaData.getString("API_KEY")?.let {
            key ->
            ApiKey.API_KEY = key
        }
    }
    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        return with(modelClass) {
            when {
                isAssignableFrom(FragmentOrActivityViewModel::class.java) -> FragmentOrActivityViewModel(application, apiHelper, localHelper)
                else -> throw IllegalArgumentException("Unknown ViewModel")
            }
        } as T
    }
}

