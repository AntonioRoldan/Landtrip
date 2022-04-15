package io.keepcoding.mvvmarchitecture.repository.local

import io.keepcoding.mvvmarchitecture.domain.TripEntity
// This is to use coroutines in our functions

interface LocalHelper {
    // TODO: Add insert and delete methods from DataNameDao
    suspend fun getEntities(): List<TripEntity>
}