package io.keepcoding.mvvmarchitecture.domain

import com.google.gson.annotations.SerializedName

data class ToursAndActivitiesResponse(

	@field:SerializedName("data")
	val data: List<ToursAndActivitiesDataItem?>? = null,

	@field:SerializedName("meta")
	val meta: ToursAndActivitiesMeta? = null
)

data class ToursAndActivitiesMeta(

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("self")
	val self: String? = null
)

data class ToursAndActivitiesGeoCode(

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null
)

data class Price(

	@field:SerializedName("amount")
	val amount: String? = null,

	@field:SerializedName("currencyCode")
	val currencyCode: String? = null
)

data class ToursAndActivitiesDataItem(

	@field:SerializedName("bookingLink")
	val bookingLink: String? = null,

	@field:SerializedName("price")
	val price: Price? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("self")
	val self: PointsOfInterestSelf? = null,

	@field:SerializedName("geoCode")
	val geoCode: ToursAndActivitiesGeoCode? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("shortDescription")
	val shortDescription: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("pictures")
	val pictures: List<String?>? = null
)

data class Self(

	@field:SerializedName("methods")
	val methods: List<String?>? = null,

	@field:SerializedName("href")
	val href: String? = null
)
