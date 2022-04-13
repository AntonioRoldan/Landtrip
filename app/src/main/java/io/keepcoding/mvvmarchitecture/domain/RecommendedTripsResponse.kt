package io.keepcoding.mvvmarchitecture.domain

import com.google.gson.annotations.SerializedName

data class RecommendedTripsResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("meta")
	val meta: Meta? = null
)

data class GeoCode(

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)

data class DataItem(

	@field:SerializedName("iataCode")
	val iataCode: String? = null,

	@field:SerializedName("subtype")
	val subtype: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("geoCode")
	val geoCode: GeoCode? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("relevance")
	val relevance: Double? = null
)

data class Links(

	@field:SerializedName("self")
	val self: String? = null
)

data class Meta(

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("links")
	val links: Links? = null
)
