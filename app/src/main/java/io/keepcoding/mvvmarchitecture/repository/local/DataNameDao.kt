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
    abstract fun getTrips(): List<TripEntity>

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

    @Query("UPDATE tours_and_activities_table SET finished = :finished")
    abstract fun updateTourActivity(finished: Boolean)

    @Query("UPDATE points_of_interest_table SET visited = :visited")
    abstract fun updatePointOfInterest(visited: Boolean)

    @Delete
    abstract fun deleteTrip(entity: TripEntity)

    @Query("DELETE FROM tours_and_activities_table WHERE id = :id")
    abstract fun  deleteTourActivity(id: String)

    @Query("DELETE FROM points_of_interest_table WHERE id = :id")
    abstract fun deletePointOfInterest(id: String)

    fun insertTripWithToursActivitiesAndPointsOfInterest(tripEntity: TripEntity){
        val toursAndActivities: MutableList<TourActivityEntity> = tripEntity.toursAndActivities!!
        val pointsOfInterest: MutableList<PointOfInterestEntity> = tripEntity.pointsOfInterest!!
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
        if(tripEntity.toursAndActivities != null && tripEntity.pointsOfInterest != null){
            for(tourAndActivity in tripEntity.toursAndActivities!!){
                deleteTourActivity(tourAndActivity.id)
            }
            for(pointOfInterest in tripEntity.pointsOfInterest!!){
                deletePointOfInterest(pointOfInterest.id)
            }
        }
        deleteTrip(tripEntity)
    }
}