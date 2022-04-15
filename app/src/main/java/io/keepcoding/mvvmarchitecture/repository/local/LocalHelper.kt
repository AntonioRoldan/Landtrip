package io.keepcoding.mvvmarchitecture.repository.local

import io.keepcoding.mvvmarchitecture.domain.TripEntity
// This is to use coroutines in our functions

interface LocalHelper {

    suspend fun getTrips(): List<TripEntity>

    suspend fun getTripWithToursActivitiesAndPointsOfInterest(tripId: String): TripEntity

    suspend fun saveTrip(tripEntity: TripEntity)

    suspend fun deleteTrip(tripEntity: TripEntity)
}