package io.keepcoding.mvvmarchitecture.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelper
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelperImpl
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelper
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelperImpl
import io.keepcoding.mvvmarchitecture.ui.FragmentOrActivityViewModel

import java.lang.IllegalArgumentException
//@Suppress("UNCHECKED_CAST")
class CustomViewModelFactory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
    private val apiHelper: ApiHelper = ApiHelperImpl()
    private val localHelper: LocalHelper = LocalHelperImpl(context = application.applicationContext)

    override fun <T : ViewModel> create(modelClass: Class<T>) : T {
        return with(modelClass) {
            when {
                //We must add another choice we may have to add isAssignableFrom(viewModel::class.java)
                FragmentOrActivityViewModel::class.java -> FragmentOrActivityViewModel(application, apiHelper, localHelper)
                else -> throw IllegalArgumentException("Unknown ViewModel")
            }
        } as T
    }
}