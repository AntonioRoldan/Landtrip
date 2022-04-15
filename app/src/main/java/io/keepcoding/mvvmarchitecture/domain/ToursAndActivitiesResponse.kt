package io.keepcoding.mvvmarchitecture.domain

import com.google.gson.annotations.SerializedName

data class ToursAndActivitiesResponse(

	@field:SerializedName("data")
	val data: List<ToursAndActivitiesDataItem?>? = null, //TODO: for element in data

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

data class ToursAndActivitiesPrice(

	@field:SerializedName("amount")
	val amount: String? = null,

	@field:SerializedName("currencyCode")
	val currencyCode: String? = null
)

data class ToursAndActivitiesDataItem(

	@field:SerializedName("bookingLink") //TODO: Show this field in activity id
	val bookingLink: String? = null,

	@field:SerializedName("price") //TODO: Show this field
	val price: ToursAndActivitiesPrice? = null,

	@field:SerializedName("name") //TODO: Show this field
	val name: String? = null,

	@field:SerializedName("rating") //TODO: Show this field
	val rating: String? = null,

	@field:SerializedName("self")
	val self: PointsOfInterestSelf? = null,

	@field:SerializedName("geoCode")
	val geoCode: ToursAndActivitiesGeoCode? = null,

	@field:SerializedName("id") // TODO: Use this field to show activity by id
	val id: String? = null,

	@field:SerializedName("shortDescription") //TODO: Show this field in activity id
	val shortDescription: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("pictures") // TODO: Show this field in activity id
	val pictures: List<String?>? = null
)

data class ToursAndActivitiesSelf(

	@field:SerializedName("methods")
	val methods: List<String?>? = null,

	@field:SerializedName("href")
	val href: String? = null
)
