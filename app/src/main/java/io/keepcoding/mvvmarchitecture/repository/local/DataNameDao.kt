package io.keepcoding.mvvmarchitecture.repository.local

import androidx.room.*
import io.keepcoding.mvvmarchitecture.domain.Entity

@Dao
abstract class DataNameDao {
    // In order to write variables within our query we do :variablePassedToBelowFunction for example SELECT * FROM table_name WHERE id = :variablePassedToBelowFunction
    @Query("SELECT * FROM data_name_table")
    abstract fun getEntities(): List<Entity>

    // These should be added to local Helper
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertEntity(entity: Entity)

    @Delete
    abstract fun deleteEntity(entity: Entity)
}