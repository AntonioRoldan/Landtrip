package io.keepcoding.mvvmarchitecture.repository.remote

import io.keepcoding.mvvmarchitecture.domain.TokenResponse
import retrofit2.Call
import io.keepcoding.mvvmarchitecture.utils.ApiKey
import retrofit2.http.*

// We create one for each API

interface Api {
    @FormUrlEncoded
    @POST("security/oauth2/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    suspend fun getData(@Query("grant_type") grantType: String,
                        @Query("client_id") clientId: String,
                        @Query("client_secret") clientSecret: String
                        ) : TokenResponse

}