package com.sanmed.footballleagues.model.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.sanmed.footballleagues.model.dao.TeamDao
import com.sanmed.footballleagues.model.entities.TeamEntity
import com.sanmed.footballleagues.view.team.ITeamView
import com.sanmed.footballleagues.view.team.TeamView
import com.sanmed.footballleagues.view.team.TeamViewHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(teamDao: TeamDao) : ILocalDataSource {
    private val allTeams: Flow<List<TeamEntity>> = teamDao.getAll()

    override fun getTeams(): LiveData<List<ITeamView>> {
        return Transformations.map(allTeams.asLiveData(), this::parseTeamViewFromTeamEntity)
    }

    private fun parseTeamViewFromTeamEntity(items: List<TeamEntity>?): List<ITeamView> {
        val result = mutableListOf<ITeamView>()
        items?.forEach {
            result.add(TeamViewHelper.parseTeamViewFromTeamEntity(it))
        }
        return result;
    }
}