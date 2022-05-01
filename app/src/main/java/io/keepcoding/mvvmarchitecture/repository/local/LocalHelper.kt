package io.keepcoding.mvvmarchitecture.repository.local

import io.keepcoding.mvvmarchitecture.domain.PointOfInterestEntity
import io.keepcoding.mvvmarchitecture.domain.TourActivityEntity
import io.keepcoding.mvvmarchitecture.domain.TripEntity
// This is to use coroutines in our functions

interface LocalHelper {

    suspend fun getTrips(): List<TripEntity>

    suspend fun getTripWithToursActivitiesAndPointsOfInterest(tripId: String): TripEntity

    suspend fun saveTrip(tripEntity: TripEntity)

    suspend fun deleteTrip(tripEntity: TripEntity)

    suspend fun saveTourActivity(tourActivityEntity: TourActivityEntity)

    suspend fun savePointOfInterest(pointOfInterestEntity: PointOfInterestEntity)

    suspend fun updateTourActivity(finished: Boolean)

    suspend fun updatePointOfInterest(visited: Boolean)

    suspend fun deleteTourActivity(id: String)

    suspend fun deletePointOfInterest(id: String)
}