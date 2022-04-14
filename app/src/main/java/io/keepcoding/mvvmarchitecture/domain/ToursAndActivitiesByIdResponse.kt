package io.keepcoding.mvvmarchitecture.domain

import com.google.gson.annotations.SerializedName

data class ToursAndActivitiesByIdResponse(

	@field:SerializedName("data")
	val data: ToursAndActivitiesByIdData? = null,

	@field:SerializedName("meta")
	val meta: ToursAndActivitiesByIdMeta? = null
)

data class ToursAndActivitiesByIdPrice(

	@field:SerializedName("amount")
	val amount: String? = null,

	@field:SerializedName("currencyCode")
	val currencyCode: String? = null
)

data class ToursAndActivitiesByIdSelf(

	@field:SerializedName("methods")
	val methods: List<String?>? = null,

	@field:SerializedName("href")
	val href: String? = null
)

data class ToursAndActivitiesByIdData(

	@field:SerializedName("bookingLink")
	val bookingLink: String? = null,

	@field:SerializedName("price")
	val price: ToursAndActivitiesByIdPrice? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("self")
	val self: ToursAndActivitiesByIdSelf? = null,

	@field:SerializedName("geoCode")
	val geoCode: ToursAndActivitiesByIdGeoCode? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("shortDescription")
	val shortDescription: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("pictures")
	val pictures: List<String?>? = null
)

data class ToursAndActivitiesByIdGeoCode(

	@field:SerializedName("latitude")
	val latitude: String? = null,

	@field:SerializedName("longitude")
	val longitude: String? = null
)

data class ToursAndActivitiesByIdMeta(

	@field:SerializedName("self")
	val self: String? = null
)
