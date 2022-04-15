package io.keepcoding.mvvmarchitecture.repository.local

import android.content.Context
import io.keepcoding.mvvmarchitecture.domain.TripEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.withContext

class LocalHelperImpl(private val context: Context) : LocalHelper {

    @InternalCoroutinesApi
    private val appDatabase: TripsRoomDatabase = TripsRoomDatabase.getInstance(context = context)

    @InternalCoroutinesApi
    override suspend fun getEntities(): List<TripEntity> {
        var entities: List<TripEntity>
        withContext(Dispatchers.IO){
            entities = appDatabase.dataNameDao().getEntities()
        }
        return entities
    }
}