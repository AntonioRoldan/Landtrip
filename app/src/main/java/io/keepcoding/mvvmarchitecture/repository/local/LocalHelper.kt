package io.keepcoding.mvvmarchitecture.repository.local

import io.keepcoding.mvvmarchitecture.domain.Entity
// This is to use coroutines in our functions

interface LocalHelper {
    // TODO: Add insert and delete methods from DataNameDao
    suspend fun getEntities(): List<Entity>
}