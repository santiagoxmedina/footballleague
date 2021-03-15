package com.sanmed.footballleagues.model.datasource

import androidx.lifecycle.LiveData
import com.sanmed.footballleagues.model.remote.Resource
import com.sanmed.footballleagues.view.team.ITeamView
import com.sanmed.footballleagues.view.team_detail.ITeamDetailView

interface IRemoteDataSource {
    fun loadTeams(leagueName: String)
    fun getTeams(): LiveData<Resource<List<ITeamView>>>
    fun getTeamDetail(): LiveData<Resource<ITeamDetailView>>
    fun loadTeamDetail(teamId: String)
}