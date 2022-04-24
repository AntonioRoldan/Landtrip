package io.keepcoding.mvvmarchitecture.domain

import com.google.gson.annotations.SerializedName

data class PointOfInterestByIdResponse(

	@field:SerializedName("data")
	val data: Data? = null
)

data class Self(

	@field:SerializedName("methods")
	val methods: List<String?>? = null,

	@field:SerializedName("href")
	val href: String? = null
)

data class Data(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("self")
	val self: Self? = null,

	@field:SerializedName("geoCode")
	val geoCode: GeoCode? = null,

	@field:SerializedName("rank")
	val rank: Int? = null,

	@field:SerializedName("subType")
	val subType: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("tags")
	val tags: List<String?>? = null
)

data class GeoCode(

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)
