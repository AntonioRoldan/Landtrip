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
}

object Constants {
    var CITY_CODE = "MAD"
}

object FragmentArguments {
    const val FROM_SERVER = "FROM_SERVER" // Tours and points of interest fragment's argument
    const val FROM_HOME = "FROM_HOME" // Since the recommended trips api call returns null for longitude, we have to pass the city name and get coordinates with another api from the home fragment
    const val LATITUDE = "LATITUDE" // Argument for tours activities and points of interest fragment and detail fragments for activity and point of interest
    const val LONGITUDE = "LONGITUDE"
    const val CITY_NAME = "CITY_NAME"
}