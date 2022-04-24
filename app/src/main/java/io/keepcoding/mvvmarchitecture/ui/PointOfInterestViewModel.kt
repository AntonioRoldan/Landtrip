package io.keepcoding.mvvmarchitecture.ui

import android.os.Parcelable
import io.keepcoding.mvvmarchitecture.utils.Constants
import kotlinx.android.parcel.Parcelize

@Parcelize // We add parcelize annotation and extend parcelable interface so we can pass the object as a parameter
data class PointOfInterestViewModel(override val viewType: Int = Constants.POINT_OF_INTEREST_VIEW_TYPE, val id: String?, val name: String?, val category: String, val rank: Int, val latitude: Double, val longitude: Double) : Parcelable, ActivitiesAndPointOfInterestItemInterface