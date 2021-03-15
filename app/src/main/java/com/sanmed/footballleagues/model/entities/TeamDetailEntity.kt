package com.sanmed.footballleagues.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TeamDetailEntity(@PrimaryKey val teamId: String,
                       @ColumnInfo(name = "name") val name: String,
                       @ColumnInfo(name = "description") val description: String,
                       @ColumnInfo(name = "foundation") val foundation: String,
                       @ColumnInfo(name = "badge") val badge: String,
                       @ColumnInfo(name = "jersey") val jersey: String,
                       @ColumnInfo(name = "links") val links: String)