package io.keepcoding.mvvmarchitecture.ui

import io.keepcoding.mvvmarchitecture.utils.Constants

data class ActivityViewModel(override val viewType: Int = Constants.ACTIVITY_VIEW_TYPE, val id: String?, val name: String?, val rating: String?, val price: String?, val currencyCode: String?) : ActivitiesAndPointOfInterestItemInterface
