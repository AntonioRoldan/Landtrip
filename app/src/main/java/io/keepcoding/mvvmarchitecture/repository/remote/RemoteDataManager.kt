package io.keepcoding.mvvmarchitecture.repository.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class RemoteDataManager {

    //TODO: Insert Api as val api: Api

    init {
        val timeout: Long = 6 * 1000

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.MILLISECONDS)
            .writeTimeout(timeout, TimeUnit.MILLISECONDS)
            .readTimeout(timeout, TimeUnit.MILLISECONDS)
            .addInterceptor(logging)
            .build()

        //TODO: Add api like this
        // val retrofitAPINAME = Retrofit.Builder()
        //      .addConverterFactory(GsonConverterFactory.create())
        //      .baseUrl("APIBASEURL")
        //      .build()
        //
        //
        // api = retrofitAPINAME.create(Api::class.java)
    }
}
