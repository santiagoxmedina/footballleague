package com.sanmed.footballleagues.model.repository

import androidx.lifecycle.LiveData
import com.sanmed.footballleagues.model.remote.Resource
import com.sanmed.footballleagues.view.team.ITeamView
import com.sanmed.footballleagues.view.team_detail.ITeamDetailView

interface IFootballRepository {
    fun preLoadData():LiveData<Resource<Boolean>>
    fun getTeams(): LiveData<Resource<List<ITeamView>>>
    fun getTeamDetail(): LiveData<Resource<ITeamDetailView>>
    fun loadTeamDetail(teamId: String)
    fun loadTeams(leagueName: String)
}