package io.keepcoding.mvvmarchitecture.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.keepcoding.mvvmarchitecture.domain.PointOfInterestEntity
import io.keepcoding.mvvmarchitecture.domain.StringListTypeConverter
import io.keepcoding.mvvmarchitecture.domain.TourActivityEntity
import io.keepcoding.mvvmarchitecture.domain.TripEntity
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

// Each entity is a table with a table name
@Database(entities = [TripEntity::class, TourActivityEntity::class, PointOfInterestEntity::class], version = 1, exportSchema = false)
@TypeConverters(StringListTypeConverter::class) // We are not using this since we removed the tag column but it's useful for learning
abstract class TripsRoomDatabase : RoomDatabase() {
    abstract fun dataNameDao() : DataNameDao

    companion object {
        private var instance: TripsRoomDatabase? = null

        @InternalCoroutinesApi
        fun getInstance(context: Context): TripsRoomDatabase {
            if(instance == null){
                synchronized(TripsRoomDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        TripsRoomDatabase::class.java,
                        "trips_db")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }
    }
}