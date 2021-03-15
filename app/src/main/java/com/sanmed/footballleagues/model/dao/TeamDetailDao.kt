package com.sanmed.footballleagues.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sanmed.footballleagues.model.entities.TeamDetailEntity
import com.sanmed.footballleagues.model.entities.TeamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDetailDao {

    @Query("SELECT * FROM teamdetailentity WHERE teamId = (:teamId)")
    fun getTeamDetail(teamId: String): Flow<TeamDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert( teamDetail: TeamDetailEntity)
}