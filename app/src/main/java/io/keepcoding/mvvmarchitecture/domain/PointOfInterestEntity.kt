package io.keepcoding.mvvmarchitecture.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "points_of_interest_table")
data class PointOfInterestEntity(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),

)
