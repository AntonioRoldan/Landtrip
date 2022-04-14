package io.keepcoding.mvvmarchitecture.domain

import com.google.gson.annotations.SerializedName

data class RecommendedTripsResponse(

	@field:SerializedName("data")
	val data: List<RecommendedTripsDataItem?>? = null,

	@field:SerializedName("meta")
	val meta: RecommendedTripsMeta? = null
)

data class RecommendedTripsMeta(

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("links")
	val links: RecommendedTripsLinks? = null
)

data class RecommendedTripsGeoCode(

	@field:SerializedName("longiture")
	val longitude: Double? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null
)

data class RecommendedTripsDataItem(

	@field:SerializedName("iataCode")
	val iataCode: String? = null,

	@field:SerializedName("subtype")
	val subtype: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("geoCode")
	val geoCode: RecommendedTripsGeoCode? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("relevance")
	val relevance: Double? = null
)

data class RecommendedTripsLinks(

	@field:SerializedName("self")
	val self: String? = null
)
