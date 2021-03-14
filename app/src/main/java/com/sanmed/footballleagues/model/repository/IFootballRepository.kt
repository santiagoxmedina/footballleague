package com.sanmed.footballleagues.model.repository

import androidx.lifecycle.LiveData
import com.sanmed.footballleagues.model.remote.Resource
import com.sanmed.footballleagues.view.team.ITeamView

interface IFootballRepository {
    fun preLoadData():LiveData<Resource<Boolean>>
    fun getTeams(): LiveData<Resource<List<ITeamView>>>
    fun loadTeams(leagueName: String)
}