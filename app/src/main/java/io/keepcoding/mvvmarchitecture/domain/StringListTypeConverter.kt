package io.keepcoding.mvvmarchitecture.domain

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object StringListTypeConverter {
    @TypeConverter
    @JvmStatic
    fun saveList(list: List<String>) : String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun getList(list: String) : List<String> {
        return Gson().fromJson(list,
        object : TypeToken<List<String?>?>() {}.type)
    }
}

