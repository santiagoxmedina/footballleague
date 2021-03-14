package com.sanmed.footballleagues.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanmed.footballleagues.model.dao.TeamDao
import com.sanmed.footballleagues.model.entities.TeamEntity

@Database(entities = [TeamEntity::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun teamDao(): TeamDao
}