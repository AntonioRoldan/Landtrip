package io.keepcoding.mvvmarchitecture.domain

import com.google.gson.annotations.SerializedName

data class TokenResponse(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("application_name")
	val applicationName: String? = null,

	@field:SerializedName("scope")
	val scope: String? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("token_type")
	val tokenType: String? = null,

	@field:SerializedName("expires_in")
	val expiresIn: Int? = null,

	@field:SerializedName("client_id")
	val clientId: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
