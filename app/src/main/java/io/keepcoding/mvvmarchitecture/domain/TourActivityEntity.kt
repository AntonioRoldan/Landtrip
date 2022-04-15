package io.keepcoding.mvvmarchitecture.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tours_and_activities_table")
class TourActivityEntity {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()

}