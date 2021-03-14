package com.sanmed.footballleagues.model.dao

import androidx.room.*
import com.sanmed.footballleagues.model.entities.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Query("SELECT * FROM teamentity")
    fun getAll(): Flow<List<TeamEntity>>

    @Query("DELETE FROM teamentity")
    suspend fun deleteAll()

    @Transaction
    suspend fun replace(items: List<TeamEntity>) {
        deleteAll()
        insertAll(items)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( items: List<TeamEntity>)
}