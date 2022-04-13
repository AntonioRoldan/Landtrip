package io.keepcoding.mvvmarchitecture.repository.local

import android.content.Context
import io.keepcoding.mvvmarchitecture.domain.Entity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.withContext

class LocalHelperImpl(private val context: Context) : LocalHelper {

    @InternalCoroutinesApi
    private val appDatabase: DataNameRoomDatabase = DataNameRoomDatabase.getInstance(context = context)

    @InternalCoroutinesApi
    override suspend fun getEntities(): List<Entity> {
        var entities: List<Entity>
        withContext(Dispatchers.IO){
            entities = appDatabase.dataNameDao().getEntities()
        }
        return entities
    }
}