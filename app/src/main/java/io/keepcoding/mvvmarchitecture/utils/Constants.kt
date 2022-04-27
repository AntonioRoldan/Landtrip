package io.keepcoding.mvvmarchitecture.utils

import io.keepcoding.mvvmarchitecture.BuildConfig


const val REQUEST_CODE = 100

object Api {
    const val AMADEUS_API_KEY = BuildConfig.AMADEUS_API_KEY
    const val AMADEUS_API_SECRET = BuildConfig.AMADEUS_API_SECRET
    const val AMADEUS_TOKEN = ""
    const val AMADEUS_API_BASE_URL = "https://test.api.amadeus.com/v1/"
    const val IMAGES_API_KEY = BuildConfig.IMAGES_API_KEY
    const val IMAGES_API_SECRET = BuildConfig.IMAGES_API_SECRET
    const val IMAGES_API_BASE_URL = "https://api.unsplash.com/"
    const val GEOLOCATION_API_KEY = BuildConfig.GEOLOCATION_API_KEY
    const val GEOLOCATION_API_BASE_URL = "http://api.openweathermap.org/geo/1.0/"
    const val MAPS_API_KEY = BuildConfig.MAPS_API_KEY
}

object Constants {
    var CITY_CODE = "MAD"
    const val ACTIVITY_VIEW_TYPE = 0
    const val POINT_OF_INTEREST_VIEW_TYPE = 1
}

object FragmentArguments {
    const val ACTIVITY_ID = ""
    const val POINT_OF_INTEREST_ID = ""
    const val TRIP_ID = ""
    const val FROM_SERVER = "FROM_SERVER" // Tours and points of interest fragment's argument
    const val FROM_RECOMMENDED_TRIPS = "FROM_RECOMMENDED_TRIPS" // Since the recommended trips api call returns null for longitude, we have to pass the city name and get coordinates with another api from the home fragment
    const val LATITUDE = "LATITUDE" // Argument for tours activities and points of interest fragment and detail fragments for activity and point of interest
    const val LONGITUDE = "LONGITUDE"
    const val CITY_NAME = "CITY_NAME"
    const val POINT_OF_INTEREST_PARCELABLE = "POINT_OF_INTEREST_PARCELABLE"
    const val ACTIVITY_PARCELABLE = "ACTIVITY_PARCELABLE"
}