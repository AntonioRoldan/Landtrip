package io.keepcoding.mvvmarchitecture.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.keepcoding.mvvmarchitecture.utils.Resource
import io.keepcoding.mvvmarchitecture.domain.Entity
import io.keepcoding.mvvmarchitecture.repository.local.LocalHelper
import io.keepcoding.mvvmarchitecture.repository.remote.ApiHelper
import kotlinx.coroutines.launch

class FragmentOrActivityViewModel(private val context: Application, private val apiHelper: ApiHelper, private val localHelper: LocalHelper) : ViewModel() {
    // private val POJORetrofitResponses : MutableLiveData<Resource<List<POJORetrofitResponseItemViewModel?>>>() to show it in a recycler view

    // fun fetchPojoRetrofitResponse() {
        // viewModelScope.launch {
            // POJORetrofitResponses.postValue(Resource.loading(null))
    //         try {
    //            val response = async { apiHelper.getEvents() }
    //            val POJORetrofitResponseItemViewModels: List<POJORetrofitResponseItemViewModel?>? = response.await().POJORetrofitResponses?.map {
    //              it?.data?.let { data ->
    //                  POJORetrofitResponseItemViewModel(data = data, title = it.title, description = it.description as String?)
    //              }
    //            }
    //            POJORetrofitResponses.postValue(Resource.success(POJORetrofitResponseItemViewModels))
    //         } catch(e: Exception) {
    //              POJORetrofitResponses.postValue(Resource.error(e.localizedMessage!!, null))
    //         }
        // }
    //  }

    //
    // fun getPOJORetrofitResponses(): LiveData<Resource<List<POJORetrofitResponseItemViewModel?>>> {
    //      return POJORetrofitResponses
    // }
    //
    //
}