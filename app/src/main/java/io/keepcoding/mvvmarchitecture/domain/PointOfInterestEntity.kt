package io.keepcoding.mvvmarchitecture.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*


@Entity(tableName = "points_of_interest_table")
data class PointOfInterestEntity(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var tripId: String? = null,
    var name: String,
    var category: String,
    var rank: Int,
    var tags: List<String>,
    var latitude: Double,
    var longitude: Double,
    var visited: Boolean
)
