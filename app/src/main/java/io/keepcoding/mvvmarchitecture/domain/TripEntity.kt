package io.keepcoding.mvvmarchitecture.domain

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

// This package contains our retrofit POJO classes

@Entity(tableName = "trips_table")
data class TripEntity(
    @PrimaryKey var id: String = UUID.randomUUID().toString(),
    var name: String?,
    @Ignore
    var toursAndActivities: MutableList<TourActivityEntity>? = mutableListOf(),
    @Ignore
    var pointsOfInterest: MutableList<PointOfInterestEntity>? = mutableListOf()
){
    constructor(id: String, name: String?) : this(id, name, mutableListOf(), mutableListOf())
}

