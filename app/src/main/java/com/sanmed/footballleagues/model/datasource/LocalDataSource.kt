package com.sanmed.footballleagues.model.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.asLiveData
import com.sanmed.footballleagues.model.dao.TeamDao
import com.sanmed.footballleagues.model.dao.TeamDetailDao
import com.sanmed.footballleagues.model.entities.TeamDetailEntity
import com.sanmed.footballleagues.model.entities.TeamEntity
import com.sanmed.footballleagues.view.team.ITeamView
import com.sanmed.footballleagues.view.team.TeamViewHelper
import com.sanmed.footballleagues.view.team_detail.ITeamDetailView
import com.sanmed.footballleagues.view.team_detail.TeamDetailViewHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val teamDao: TeamDao,private val teamDetailDao: TeamDetailDao) : ILocalDataSource {
    private val allTeams: Flow<List<TeamEntity>> = teamDao.getAll()

    override fun getTeams(): LiveData<List<ITeamView>> {
        return Transformations.map(allTeams.asLiveData(), this::parseTeamViewFromTeamEntity)
    }

    override fun replace(teams: List<ITeamView>) {

        GlobalScope.launch {
            val replace = mutableListOf<TeamEntity>()
            teams.forEach {
                replace.add(TeamViewHelper.parseTeamEntityFromTeamView(it))
            }
            teamDao.replace(replace)
        }

    }

    private fun parseTeamViewFromTeamEntity(items: List<TeamEntity>?): List<ITeamView> {
        val result = mutableListOf<ITeamView>()
        items?.forEach {
            result.add(TeamViewHelper.parseTeamViewFromTeamEntity(it))
        }
        return result;
    }


}