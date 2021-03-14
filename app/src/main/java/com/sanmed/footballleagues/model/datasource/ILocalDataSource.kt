package com.sanmed.footballleagues.model.datasource

import androidx.lifecycle.LiveData
import com.sanmed.footballleagues.model.entities.TeamEntity
import com.sanmed.footballleagues.view.team.ITeamView

interface ILocalDataSource {
    fun getTeams(): LiveData<List<ITeamView>>
    fun replace(teams: List<ITeamView>)
}