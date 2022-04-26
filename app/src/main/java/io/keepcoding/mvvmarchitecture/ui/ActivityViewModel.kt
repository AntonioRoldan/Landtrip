package io.keepcoding.mvvmarchitecture.ui

import android.os.Parcelable
import io.keepcoding.mvvmarchitecture.utils.Constants
import kotlinx.android.parcel.Parcelize

//We will make an api call passing the id from the activity detail fragment
// If we get the activity from the local repository we pass it as a parameter so we are going to parcelize it and extend the parcelable interface
@Parcelize
data class ActivityViewModel(override val viewType: Int = Constants.ACTIVITY_VIEW_TYPE, val id: String? = "", val name: String?, val rating: String?, val price: String?, val currencyCode: String?, var visited: Boolean = false) : Parcelable, ActivitiesAndPointOfInterestItemInterface
