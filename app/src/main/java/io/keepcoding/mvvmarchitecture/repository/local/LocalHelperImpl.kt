package io.keepcoding.mvvmarchitecture.repository.local

import android.content.Context
import io.keepcoding.mvvmarchitecture.domain.PointOfInterestEntity
import io.keepcoding.mvvmarchitecture.domain.TourActivityEntity
import io.keepcoding.mvvmarchitecture.domain.TripEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.withContext

@Suppress("OPT_IN_IS_NOT_ENABLED")
class LocalHelperImpl(private val context: Context) : LocalHelper {

    @InternalCoroutinesApi
    private val appDatabase: TripsRoomDatabase = TripsRoomDatabase.getInstance(context = context)

    @OptIn(InternalCoroutinesApi::class)
    override suspend fun getTrips(): List<TripEntity> {
        var trips: List<TripEntity>
        withContext(Dispatchers.IO){
            trips = appDatabase.dataNameDao().getTrips()
        }
        return trips
    }

    @OptIn(InternalCoroutinesApi::class)
    override suspend fun getTripWithToursActivitiesAndPointsOfInterest(tripId: String): TripEntity {
        var trip: TripEntity
        withContext(Dispatchers.IO){
            trip = appDatabase.dataNameDao().getLocalTripWithToursActivitiesAndPointsOfInterest(tripId)
        }
        return trip
    }

    @OptIn(InternalCoroutinesApi::class)
    override suspend fun saveTrip(tripEntity: TripEntity) {
        withContext(Dispatchers.IO){
            appDatabase.dataNameDao().insertTripWithToursActivitiesAndPointsOfInterest(tripEntity)
        }
    }

    @OptIn(InternalCoroutinesApi::class)
    override suspend fun deleteTrip(tripEntity: TripEntity) {
        withContext(Dispatchers.IO){
            appDatabase.dataNameDao().deleteTripWithToursActivitiesAndPointsOfInterest(tripEntity)
        }
    }

    @OptIn(InternalCoroutinesApi::class)
    override suspend fun saveTourActivity(tourActivityEntity: TourActivityEntity) {
        withContext(Dispatchers.IO) {
            appDatabase.dataNameDao().insertTourActivity(tourActivityEntity)
        }
    }

    @OptIn(InternalCoroutinesApi::class)
    override suspend fun savePointOfInterest(pointOfInterestEntity: PointOfInterestEntity) {
        withContext(Dispatchers.IO) {
            appDatabase.dataNameDao().insertPointOfInterest(pointOfInterestEntity)
        }
    }

    @OptIn(InternalCoroutinesApi::class)
    override suspend fun updateTourActivity(finished: Boolean) {
        withContext(Dispatchers.IO){
            appDatabase.dataNameDao().updateTourActivity(finished)
        }
    }

    @OptIn(InternalCoroutinesApi::class)
    override suspend fun updatePointOfInterest(visited: Boolean) {
        withContext(Dispatchers.IO) {
            appDatabase.dataNameDao().updatePointOfInterest(visited)
        }
    }

    @OptIn(InternalCoroutinesApi::class)
    override suspend fun deleteTourActivity(id: String) {
        withContext(Dispatchers.IO) {
            appDatabase.dataNameDao().deleteTourActivity(id)
        }
    }

    @OptIn(InternalCoroutinesApi::class)
    override suspend fun deletePointOfInterest(id: String) {
        withContext(Dispatchers.IO){
            appDatabase.dataNameDao().deletePointOfInterest(id)
        }
    }
}