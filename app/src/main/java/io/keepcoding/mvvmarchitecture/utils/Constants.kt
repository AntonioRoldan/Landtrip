package io.keepcoding.mvvmarchitecture.utils


const val REQUEST_CODE = 100

class ApiKey(private val apiKey: String) {
    var shared: ApiKey = ApiKey(this.apiKey)
    val API_KEY = ""
    val API_BASE_URL = "https://test.api.amadeus.com/v1/"
}