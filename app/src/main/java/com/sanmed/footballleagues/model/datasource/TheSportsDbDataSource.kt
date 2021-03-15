package com.sanmed.footballleagues.model.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sanmed.footballleagues.model.conection.RetrofitHelper
import com.sanmed.footballleagues.model.dto.team.TeamDTO
import com.sanmed.footballleagues.model.dto.team.TeamsRequest
import com.sanmed.footballleagues.model.dto.team.TeamsResponse
import com.sanmed.footballleagues.model.dto.team_detail.TeamDetailRequest
import com.sanmed.footballleagues.model.dto.team_detail.TeamDetailResponse
import com.sanmed.footballleagues.model.remote.Resource
import com.sanmed.footballleagues.model.service.TheSportsDbService
import com.sanmed.footballleagues.view.team.ITeamView
import com.sanmed.footballleagues.view.team.TeamViewHelper
import com.sanmed.footballleagues.view.team_detail.ITeamDetailView
import com.sanmed.footballleagues.view.team_detail.TeamDetailViewHelper
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TheSportsDbDataSource @Inject constructor() : IRemoteDataSource {
    private val retrofit = RetrofitHelper.getRetrofit("https://www.thesportsdb.com/")
    private val service: TheSportsDbService = retrofit.create(TheSportsDbService::class.java)
    private var cacheItemDTOS: Array<TeamDTO?> = Array(0) { TeamDTO() }
    private val resultTeams = MutableLiveData<Resource<List<ITeamView>>>()
    private val resultTeamDetail = MutableLiveData<Resource<ITeamDetailView>>()

    override fun loadTeams(leagueName: String) {
        leagueName.let {
            val request = teamRequest(leagueName)
            val call = service.getLeagueTeams(request.leagueName)

            call.enqueue(object : Callback<TeamsResponse> {
                override fun onResponse(
                    call: Call<TeamsResponse>,
                    response: Response<TeamsResponse>
                ) {
                    if (response.isSuccessful) {
                        setTeams(response)
                    } else {
                        resultTeams.value = Resource.error(getError(response.errorBody()), null)
                    }
                }

                override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                    var error = "Unknown"
                    t.message?.let {
                        error = it
                    }
                    resultTeams.value = Resource.error(error, null)
                }
            })
        }
    }

    override fun getTeams(): LiveData<Resource<List<ITeamView>>> {
        return resultTeams
    }

    override fun getTeamDetail(): LiveData<Resource<ITeamDetailView>> {
        return resultTeamDetail
    }

    override fun loadTeamDetail(teamId: String) {
        teamId.let {
            val request = teamDetailRequest(teamId)
            val call = service.getTeamDetail(request.teamId)

            call.enqueue(object : Callback<TeamDetailResponse> {
                override fun onResponse(
                    call: Call<TeamDetailResponse>,
                    response: Response<TeamDetailResponse>
                ) {
                    if (response.isSuccessful) {
                        setTeamsDetail(response)
                    } else {
                        resultTeamDetail.value = Resource.error(getError(response.errorBody()),null)
                    }
                }

                override fun onFailure(call: Call<TeamDetailResponse>, t: Throwable) {
                    var error = "Unknown"
                    t.message?.let {
                        error = it
                    }
                    resultTeamDetail.value = Resource.error(error,null)
                }
            })
        }
    }

    private fun getError(response: ResponseBody?): String {
        if (response != null) {
            return response.toString()
        }
        return "Unknown"
    }

    private fun setTeams(response: Response<TeamsResponse>) {
        if (response.body() != null) {
            val result = mutableListOf<ITeamView>()
            response.body()?.teams?.let { team ->
                team.forEach {
                    it?.let { result.add(TeamViewHelper.parseTeamViewFromTeamDTO(it)) }
                }
                cacheItemDTOS = team
            }
            resultTeams.value = Resource.success(result)
        }
    }

    private fun setTeamsDetail(response: Response<TeamDetailResponse>) {
        if (response.body() != null) {
            response.body()?.teams?.let { teamDetail ->
                val result = Resource.success(teamDetail.first()?.let {
                    TeamDetailViewHelper.parseTeamDetailViewFromTeamDTO(
                        it
                    )
                })
                resultTeamDetail.value = result
            }

        }
    }

    private fun teamRequest(leagueName: String): TeamsRequest {
        val request = TeamsRequest()
        request.leagueName = leagueName
        return request
    }

    private fun teamDetailRequest(teamId: String): TeamDetailRequest {
        val request = TeamDetailRequest()
        request.teamId = teamId
        return request
    }
}