package io.keepcoding.mvvmarchitecture.domain

import com.google.gson.annotations.SerializedName

data class GeoLocationResponse(

	@field:SerializedName("items")
	val items: List<ItemsItem?>? = null
)

data class SecondaryUnitsItem(

	@field:SerializedName("qq")
	val qq: String? = null,

	@field:SerializedName("start")
	val start: Int? = null,

	@field:SerializedName("end")
	val end: Int? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class HouseNumberItem(

	@field:SerializedName("qq")
	val qq: String? = null,

	@field:SerializedName("start")
	val start: Int? = null,

	@field:SerializedName("end")
	val end: Int? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class AccessItem(

	@field:SerializedName("lng")
	val lng: Int? = null,

	@field:SerializedName("lat")
	val lat: Int? = null
)

data class DistrictItem(

	@field:SerializedName("qq")
	val qq: String? = null,

	@field:SerializedName("start")
	val start: Int? = null,

	@field:SerializedName("end")
	val end: Int? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class Parsing(

	@field:SerializedName("country")
	val country: List<CountryItem?>? = null,

	@field:SerializedName("city")
	val city: List<CityItem?>? = null,

	@field:SerializedName("postalCode")
	val postalCode: List<PostalCodeItem?>? = null,

	@field:SerializedName("county")
	val county: List<CountyItem?>? = null,

	@field:SerializedName("houseNumber")
	val houseNumber: List<HouseNumberItem?>? = null,

	@field:SerializedName("building")
	val building: List<BuildingItem?>? = null,

	@field:SerializedName("subdistrict")
	val subdistrict: List<SubdistrictItem?>? = null,

	@field:SerializedName("street")
	val street: List<StreetItem?>? = null,

	@field:SerializedName("secondaryUnits")
	val secondaryUnits: List<SecondaryUnitsItem?>? = null,

	@field:SerializedName("district")
	val district: List<DistrictItem?>? = null,

	@field:SerializedName("ontologyName")
	val ontologyName: List<OntologyNameItem?>? = null,

	@field:SerializedName("block")
	val block: List<BlockItem?>? = null,

	@field:SerializedName("state")
	val state: List<StateItem?>? = null,

	@field:SerializedName("placeName")
	val placeName: List<PlaceNameItem?>? = null,

	@field:SerializedName("subblock")
	val subblock: List<SubblockItem?>? = null
)

data class CountyItem(

	@field:SerializedName("qq")
	val qq: String? = null,

	@field:SerializedName("start")
	val start: Int? = null,

	@field:SerializedName("end")
	val end: Int? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class TimeZone(

	@field:SerializedName("utcOffset")
	val utcOffset: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class CityItem(

	@field:SerializedName("qq")
	val qq: String? = null,

	@field:SerializedName("start")
	val start: Int? = null,

	@field:SerializedName("end")
	val end: Int? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class CountryInfo(

	@field:SerializedName("alpha2")
	val alpha2: String? = null,

	@field:SerializedName("alpha3")
	val alpha3: String? = null
)

data class CategoriesItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("primary")
	val primary: Boolean? = null
)

data class Scoring(

	@field:SerializedName("queryScore")
	val queryScore: Int? = null,

	@field:SerializedName("fieldScore")
	val fieldScore: FieldScore? = null
)

data class CountryItem(

	@field:SerializedName("qq")
	val qq: String? = null,

	@field:SerializedName("start")
	val start: Int? = null,

	@field:SerializedName("end")
	val end: Int? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class PlaceNameItem(

	@field:SerializedName("qq")
	val qq: String? = null,

	@field:SerializedName("start")
	val start: Int? = null,

	@field:SerializedName("end")
	val end: Int? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class PostalCodeItem(

	@field:SerializedName("qq")
	val qq: String? = null,

	@field:SerializedName("start")
	val start: Int? = null,

	@field:SerializedName("end")
	val end: Int? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class StreetItem(

	@field:SerializedName("qq")
	val qq: String? = null,

	@field:SerializedName("start")
	val start: Int? = null,

	@field:SerializedName("end")
	val end: Int? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class OntologyNameItem(

	@field:SerializedName("qq")
	val qq: String? = null,

	@field:SerializedName("start")
	val start: Int? = null,

	@field:SerializedName("end")
	val end: Int? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class Position(

	@field:SerializedName("lng")
	val lng: Int? = null,

	@field:SerializedName("lat")
	val lat: Int? = null
)

data class BlockItem(

	@field:SerializedName("qq")
	val qq: String? = null,

	@field:SerializedName("start")
	val start: Int? = null,

	@field:SerializedName("end")
	val end: Int? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class StreetInfoItem(

	@field:SerializedName("streetTypePrecedes")
	val streetTypePrecedes: Boolean? = null,

	@field:SerializedName("streetTypeAttached")
	val streetTypeAttached: Boolean? = null,

	@field:SerializedName("streetType")
	val streetType: String? = null,

	@field:SerializedName("prefix")
	val prefix: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("suffix")
	val suffix: String? = null,

	@field:SerializedName("baseName")
	val baseName: String? = null,

	@field:SerializedName("direction")
	val direction: String? = null
)

data class FoodTypesItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("primary")
	val primary: Boolean? = null
)

data class SubdistrictItem(

	@field:SerializedName("qq")
	val qq: String? = null,

	@field:SerializedName("start")
	val start: Int? = null,

	@field:SerializedName("end")
	val end: Int? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class ItemsItem(

	@field:SerializedName("mapView")
	val mapView: MapView? = null,

	@field:SerializedName("scoring")
	val scoring: Scoring? = null,

	@field:SerializedName("address")
	val address: Address? = null,

	@field:SerializedName("access")
	val access: List<AccessItem?>? = null,

	@field:SerializedName("distance")
	val distance: Int? = null,

	@field:SerializedName("timeZone")
	val timeZone: TimeZone? = null,

	@field:SerializedName("houseNumberType")
	val houseNumberType: String? = null,

	@field:SerializedName("addressBlockType")
	val addressBlockType: String? = null,

	@field:SerializedName("administrativeAreaType")
	val administrativeAreaType: String? = null,

	@field:SerializedName("streetInfo")
	val streetInfo: List<StreetInfoItem?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("foodTypes")
	val foodTypes: List<FoodTypesItem?>? = null,

	@field:SerializedName("parsing")
	val parsing: Parsing? = null,

	@field:SerializedName("houseNumberFallback")
	val houseNumberFallback: Boolean? = null,

	@field:SerializedName("politicalView")
	val politicalView: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("position")
	val position: Position? = null,

	@field:SerializedName("categories")
	val categories: List<CategoriesItem?>? = null,

	@field:SerializedName("countryInfo")
	val countryInfo: CountryInfo? = null,

	@field:SerializedName("resultType")
	val resultType: String? = null,

	@field:SerializedName("localityType")
	val localityType: String? = null
)

data class BuildingItem(

	@field:SerializedName("qq")
	val qq: String? = null,

	@field:SerializedName("start")
	val start: Int? = null,

	@field:SerializedName("end")
	val end: Int? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class SubblockItem(

	@field:SerializedName("qq")
	val qq: String? = null,

	@field:SerializedName("start")
	val start: Int? = null,

	@field:SerializedName("end")
	val end: Int? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class StateItem(

	@field:SerializedName("qq")
	val qq: String? = null,

	@field:SerializedName("start")
	val start: Int? = null,

	@field:SerializedName("end")
	val end: Int? = null,

	@field:SerializedName("value")
	val value: String? = null
)

data class FieldScore(

	@field:SerializedName("country")
	val country: Int? = null,

	@field:SerializedName("streets")
	val streets: List<Int?>? = null,

	@field:SerializedName("city")
	val city: Int? = null,

	@field:SerializedName("postalCode")
	val postalCode: Int? = null,

	@field:SerializedName("county")
	val county: Int? = null,

	@field:SerializedName("houseNumber")
	val houseNumber: Int? = null,

	@field:SerializedName("building")
	val building: Int? = null,

	@field:SerializedName("countyCode")
	val countyCode: Int? = null,

	@field:SerializedName("unit")
	val unit: Int? = null,

	@field:SerializedName("countryCode")
	val countryCode: Int? = null,

	@field:SerializedName("subdistrict")
	val subdistrict: Int? = null,

	@field:SerializedName("district")
	val district: Int? = null,

	@field:SerializedName("ontologyName")
	val ontologyName: Int? = null,

	@field:SerializedName("stateCode")
	val stateCode: Int? = null,

	@field:SerializedName("block")
	val block: Int? = null,

	@field:SerializedName("state")
	val state: Int? = null,

	@field:SerializedName("placeName")
	val placeName: Int? = null,

	@field:SerializedName("subblock")
	val subblock: Int? = null
)

data class MapView(

	@field:SerializedName("east")
	val east: Int? = null,

	@field:SerializedName("south")
	val south: Int? = null,

	@field:SerializedName("north")
	val north: Int? = null,

	@field:SerializedName("west")
	val west: Int? = null
)

data class Address(

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("postalCode")
	val postalCode: String? = null,

	@field:SerializedName("county")
	val county: String? = null,

	@field:SerializedName("houseNumber")
	val houseNumber: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("building")
	val building: String? = null,

	@field:SerializedName("countyCode")
	val countyCode: String? = null,

	@field:SerializedName("countryCode")
	val countryCode: String? = null,

	@field:SerializedName("subdistrict")
	val subdistrict: String? = null,

	@field:SerializedName("street")
	val street: String? = null,

	@field:SerializedName("district")
	val district: String? = null,

	@field:SerializedName("stateCode")
	val stateCode: String? = null,

	@field:SerializedName("block")
	val block: String? = null,

	@field:SerializedName("countryName")
	val countryName: String? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("subblock")
	val subblock: String? = null
)
