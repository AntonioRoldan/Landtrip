package io.keepcoding.mvvmarchitecture.domain

import com.google.gson.annotations.SerializedName

data class ToursAndActivitiesByIdResponse(

	@field:SerializedName("data")
	val data: ToursAndActivitiesByIdData? = null,

	@field:SerializedName("meta")
	val meta: ToursAndActivitiesByIdMeta? = null
)

data class ToursAndActivitiesByIdPrice(

	@field:SerializedName("amount") //TODO: Show this
	val amount: String? = null,

	@field:SerializedName("currencyCode") //TODO: Show this
	val currencyCode: String? = null
)

data class ToursAndActivitiesByIdSelf(

	@field:SerializedName("methods")
	val methods: List<String?>? = null,

	@field:SerializedName("href")
	val href: String? = null
)

data class ToursAndActivitiesByIdData(

	@field:SerializedName("bookingLink") //TODO: Show this
	val bookingLink: String? = null,

	@field:SerializedName("price") //TODO: Show this price.amount and price.currencyCode
	val price: ToursAndActivitiesByIdPrice? = null,

	@field:SerializedName("name") //TODO: Show this
	val name: String? = null,

	@field:SerializedName("rating") //TODO: Show this
	val rating: String? = null,

	@field:SerializedName("self")
	val self: ToursAndActivitiesByIdSelf? = null,

	@field:SerializedName("geoCode") //TODO: Use this to show map geoCode.latitude geoCode.longitude
	val geoCode: ToursAndActivitiesByIdGeoCode? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("shortDescription") //TODO: Show this
	val shortDescription: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("pictures") //TODO: Show pictures on a carousel
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
