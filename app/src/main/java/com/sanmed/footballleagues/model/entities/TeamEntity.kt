package com.sanmed.footballleagues.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TeamEntity(@PrimaryKey val teamId: String,
                 @ColumnInfo(name = "name") val teamName: String,
                 @ColumnInfo(name = "price") val teamStadium: String,
                 @ColumnInfo(name = "link") val teamBadge: String,) {

}