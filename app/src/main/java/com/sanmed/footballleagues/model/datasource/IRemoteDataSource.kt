package com.sanmed.footballleagues.model.datasource

interface IRemoteDataSource {
    fun loadTeams(leagueName: String)
}