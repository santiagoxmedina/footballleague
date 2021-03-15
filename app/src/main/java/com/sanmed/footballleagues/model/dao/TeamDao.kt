package com.sanmed.footballleagues.model.dao

import androidx.room.*
import com.sanmed.footballleagues.model.entities.TeamDetailEntity
import com.sanmed.footballleagues.model.entities.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Query("SELECT * FROM teamentity")
    fun getAll(): Flow<List<TeamEntity>>

    @Query("DELETE FROM teamentity")
    suspend fun deleteAll()

    @Transaction
    suspend fun replace(teams: List<TeamEntity>) {
        deleteAll()
        insertAll(teams)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( teams: List<TeamEntity>)
}