package io.keepcoding.mvvmarchitecture.domain

import com.google.gson.annotations.SerializedName

data class PointsOfInterestResponse(

	@field:SerializedName("data")
	val data: List<PointsOfInterestDataItem?>? = null,

	@field:SerializedName("meta")
	val meta: PointsOfInterestMeta? = null
)

data class PointsOfInterestLinks(

	@field:SerializedName("next")
	val next: String? = null,

	@field:SerializedName("last")
	val last: String? = null,

	@field:SerializedName("self")
	val self: String? = null,

	@field:SerializedName("up")
	val up: String? = null,

	@field:SerializedName("first")
	val first: String? = null
)

data class PointsOfInterestGeoCode(

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)

data class PointsOfInterestDataItem(

	@field:SerializedName("name") //TODO: Show this
	val name: String? = null,

	@field:SerializedName("self")
	val self: PointsOfInterestSelf? = null,

	@field:SerializedName("geoCode") //TODO: Use this for detail screen's map
	val geoCode: PointsOfInterestGeoCode? = null,

	@field:SerializedName("rank") //TODO: Show this
	val rank: Int? = null,

	@field:SerializedName("subType")
	val subType: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("category") //TODO: Show this
	val category: String? = null,

	@field:SerializedName("tags") //TODO: Show some tags
	val tags: List<String?>? = null
)

data class PointsOfInterestSelf(

	@field:SerializedName("methods")
	val methods: List<String?>? = null,

	@field:SerializedName("href")
	val href: String? = null
)

data class PointsOfInterestMeta(

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("links")
	val links: PointsOfInterestLinks? = null
)
