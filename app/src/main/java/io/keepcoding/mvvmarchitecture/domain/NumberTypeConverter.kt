package io.keepcoding.mvvmarchitecture.domain

import androidx.room.TypeConverter

object NumberTypeConverter {
    @TypeConverter
    @JvmStatic
    fun saveNumber(number: Number) : String {
        return number.toString()
    }
}