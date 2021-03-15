package com.sanmed.footballleagues.model.service

import com.sanmed.footballleagues.model.dto.team.TeamsResponse
import com.sanmed.footballleagues.model.dto.team_detail.TeamDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheSportsDbService {
    @GET("/api/v1/json/1/search_all_teams.php")
    fun getLeagueTeams(@Query("l") leagueName: String): Call<TeamsResponse>

    @GET("api/v1/json/1/lookupteam.php")
    fun getTeamDetail(@Query("id") teamId: String): Call<TeamDetailResponse>

}