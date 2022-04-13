package io.keepcoding.mvvmarchitecture.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

// This package contains our retrofit POJO classes

@Entity(tableName = "data_name_table")
class Entity {
    @PrimaryKey var id: String = UUID.randomUUID().toString()
}