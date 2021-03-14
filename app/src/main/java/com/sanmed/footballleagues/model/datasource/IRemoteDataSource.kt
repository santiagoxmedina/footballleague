package com.sanmed.footballleagues.model.datasource

import androidx.lifecycle.LiveData
import com.sanmed.footballleagues.model.remote.Resource
import com.sanmed.footballleagues.view.team.ITeamView

interface IRemoteDataSource {
    fun loadTeams(leagueName: String)
    fun getTeams(): LiveData<Resource<List<ITeamView>>>
}