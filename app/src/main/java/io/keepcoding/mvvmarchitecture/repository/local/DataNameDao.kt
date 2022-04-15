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

    @Query("SELECT * FROM tours_and_activities_table WHERE tripId = :tripId")
    abstract fun getLocalToursAndActivities(tripId: String) : List<TourActivityEntity>

    @Query("SELECT * FROM points_of_interest_table WHERE tripId = :tripId")
    abstract fun getLocalPointsOfInterest(tripId: String) : List<PointOfInterestEntity>

    @Query("SELECT * FROM trips_table WHERE id = :tripId")
    abstract fun getTrip(tripId: String): TripEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTrip(tripEntity: TripEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTourActivity(tourActivityEntity: TourActivityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPointOfInterest(pointOfInterest: PointOfInterestEntity)

    @Delete
    abstract fun deleteTrip(entity: TripEntity)

    @Delete
    abstract fun deleteTourActivity(tourActivityEntity: TourActivityEntity)

    @Delete
    abstract fun deletePointOfInterest(pointOfInterest: PointOfInterestEntity)

    fun insertTripWithToursActivitiesAndPointsOfInterest(tripEntity: TripEntity){
        var toursAndActivities: MutableList<TourActivityEntity> = tripEntity.toursAndActivities
        var pointsOfInterest: MutableList<PointOfInterestEntity> = tripEntity.pointsOfInterest
        for(tourAndActivity in toursAndActivities){
            tourAndActivity.tripId = tripEntity.id
            insertTourActivity(tourAndActivity)
        }
        for(pointOfInterest in pointsOfInterest){
            pointOfInterest.tripId = tripEntity.id
            insertPointOfInterest(pointOfInterest)
        }
        insertTrip(tripEntity)
    }

    fun getLocalTripWithToursActivitiesAndPointsOfInterest(tripId: String) : TripEntity {
        val trip: TripEntity = getTrip(tripId)
        val toursAndActivities: List<TourActivityEntity> = getLocalToursAndActivities(tripId)
        val pointsOfInterest: List<PointOfInterestEntity> = getLocalPointsOfInterest(tripId)
        trip.toursAndActivities = toursAndActivities.toMutableList()
        trip.pointsOfInterest = pointsOfInterest.toMutableList()
        return trip
    }

    fun deleteTripWithToursActivitiesAndPointsOfInterest(tripEntity: TripEntity){
        for(tourAndActivity in tripEntity.toursAndActivities){
            deleteTourActivity(tourAndActivity)
        }
        for(pointOfInterest in tripEntity.pointsOfInterest){
            deletePointOfInterest(pointOfInterest)
        }
        deleteTrip(tripEntity)
    }
}