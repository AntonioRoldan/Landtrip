package io.keepcoding.mvvmarchitecture.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.keepcoding.mvvmarchitecture.domain.Entity
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

// Each entity is a table with a table name
@Database(entities = [Entity::class], version = 1, exportSchema = false)
abstract class DataNameRoomDatabase : RoomDatabase() {
    abstract fun dataNameDao() : DataNameDao

    companion object {
        private var instance: DataNameRoomDatabase? = null

        @InternalCoroutinesApi
        fun getInstance(context: Context): DataNameRoomDatabase {
            if(instance == null){
                synchronized(DataNameRoomDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        DataNameRoomDatabase::class.java,
                        "data_name_db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }
    }
}