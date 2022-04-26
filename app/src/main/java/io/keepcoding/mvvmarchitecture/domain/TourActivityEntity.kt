package io.keepcoding.mvvmarchitecture.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tours_and_activities_table")
data class TourActivityEntity(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var tripId: String? = null, //Reference
    var name: String,
    var shortDescription: String,
    var rating: Double,
    var latitude: Double,
    var longitude: Double,
    var image: String,
    var price: String,
    var currencyCode: String,
    var finished: Boolean
)
