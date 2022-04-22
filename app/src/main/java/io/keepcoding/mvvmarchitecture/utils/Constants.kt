package io.keepcoding.mvvmarchitecture.utils


const val REQUEST_CODE = 100

object Api {
    var AMADEUS_API_KEY = ""
    var AMADEUS_API_SECRET = ""
    var AMADEUS_TOKEN = ""
    const val AMADEUS_API_BASE_URL = "https://test.api.amadeus.com/v1/"
    var IMAGES_API_KEY = ""
    var IMAGES_API_SECRET = ""
    const val IMAGES_API_BASE_URL = "https://api.unsplash.com/"
    var GEOLOCATION_API_KEY = ""
    var GEOLOCATION_API_SECRET = ""
    const val GEOLOCATION_API_BASE_URL = "http://api.openweathermap.org/geo/1.0/"
}

object Constants {
    var CITY_CODE = "MAD"
}

object FragmentArguments {
    val FROM_SERVER = "FROM_SERVER" // Tours and points of interest fragment's argument
}