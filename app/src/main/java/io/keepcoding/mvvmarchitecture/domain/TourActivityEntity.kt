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
    var latitude: Number,
    var longitude: Number,
    var image: String,
)
