package com.sanmed.footballleagues.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanmed.footballleagues.model.dao.TeamDao
import com.sanmed.footballleagues.model.dao.TeamDetailDao
import com.sanmed.footballleagues.model.entities.TeamDetailEntity
import com.sanmed.footballleagues.model.entities.TeamEntity

@Database(entities = [TeamEntity::class,TeamDetailEntity::class], version = 3,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
    abstract fun teamDetailDao(): TeamDetailDao
}