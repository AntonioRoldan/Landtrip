package io.keepcoding.mvvmarchitecture.ui

import io.keepcoding.mvvmarchitecture.utils.Constants

data class PointOfInterestViewModel(override val viewType: Int = Constants.POINT_OF_INTEREST_VIEW_TYPE, val id: String?, val name: String?, val category: String, val rank: Double) : ActivitiesAndPointOfInterestItemInterface