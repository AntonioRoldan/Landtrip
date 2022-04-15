package io.keepcoding.mvvmarchitecture.repository.local

import android.content.Context
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
            trip = appDatabase.dataNameDao().getTrip(tripId)
        }
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
}