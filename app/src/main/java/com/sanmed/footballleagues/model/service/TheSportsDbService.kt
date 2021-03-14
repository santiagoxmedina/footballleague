package com.sanmed.footballleagues.model.service

import com.sanmed.footballleagues.model.dto.TeamsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheSportsDbService {
    @GET("/api/v1/json/1/search_all_teams.php")
    fun getLeagueTeams(@Query("l") leagueName: String): Call<TeamsResponse>

}