package io.keepcoding.mvvmarchitecture.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// This package contains our retrofit POJO classes

@Entity(tableName = "trips_table")
class TripEntity {

    @PrimaryKey var id: String = UUID.randomUUID().toString()

    var toursAndActivities: MutableList<TourActivityEntity> = mutableListOf()

    var pointsOfInterest: MutableList<PointOfInterestEntity> = mutableListOf()

}