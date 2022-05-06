package io.keepcoding.mvvmarchitecture.repository.remote

import io.keepcoding.mvvmarchitecture.utils.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteDataManager {

    val amadeusApi: AmadeusApi
    val imagesApi: ImagesApi
    val geoLocationApi: GeoLocationApi

    init {
        val timeout: Long = 10 * 1000

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .addInterceptor(logging)
            .build()

        val retrofitAmadeusApi = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Api.AMADEUS_API_BASE_URL)
            .build()

        val retrofitImagesApi = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Api.IMAGES_API_BASE_URL)
            .build()

        val retrofitGeoLocationApi = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Api.GEOLOCATION_API_BASE_URL)
            .build()

        amadeusApi = retrofitAmadeusApi.create(AmadeusApi::class.java)
        imagesApi = retrofitImagesApi.create(ImagesApi::class.java)
        geoLocationApi = retrofitGeoLocationApi.create(GeoLocationApi::class.java)
    }
}
