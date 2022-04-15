package io.keepcoding.mvvmarchitecture.repository.local

import android.graphics.Point
import androidx.room.*
import io.keepcoding.mvvmarchitecture.domain.PointOfInterestEntity
import io.keepcoding.mvvmarchitecture.domain.TourActivityEntity
import io.keepcoding.mvvmarchitecture.domain.TripEntity

@Dao
abstract class DataNameDao {
    // In order to write variables within our query we do :variablePassedToBelowFunction for example SELECT * FROM table_name WHERE id = :variablePassedToBelowFunction
    @Query("SELECT * FROM trips_table")
    abstract fun getEntities(): List<TripEntity>

    // These should be added to local Helper
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEntity(entity: TripEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTourActivity(tourActivityEntity: TourActivityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPointOfInterest(pointOfInterest: PointOfInterestEntity)

    @Delete
    abstract fun deleteEntity(entity: TripEntity)

    @Delete
    abstract fun deleteTourActivity(tourActivityEntity: TourActivityEntity)

    @Delete
    abstract fun deletePointOfInterest(pointOfInterest: PointOfInterestEntity)
}