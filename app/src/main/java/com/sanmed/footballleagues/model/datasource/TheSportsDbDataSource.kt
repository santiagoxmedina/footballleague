package com.sanmed.footballleagues.model.datasource

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sanmed.footballleagues.model.conection.RetrofitHelper
import com.sanmed.footballleagues.model.dto.TeamDTO
import com.sanmed.footballleagues.model.dto.TeamsRequest
import com.sanmed.footballleagues.model.dto.TeamsResponse
import com.sanmed.footballleagues.model.remote.Resource
import com.sanmed.footballleagues.model.service.TheSportsDbService
import com.sanmed.footballleagues.view.team.ITeamView
import com.sanmed.footballleagues.view.team.TeamViewHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TheSportsDbDataSource @Inject constructor():IRemoteDataSource{
    private val retrofit = RetrofitHelper.getRetrofit("https://www.thesportsdb.com/")
    private val service: TheSportsDbService = retrofit.create(TheSportsDbService::class.java)
    private var cacheItemDTOS:Array<TeamDTO?> = Array(0){ TeamDTO() }
    private val resultTeams = MutableLiveData<Resource<List<ITeamView>>>()

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
                    }
                }

                override fun onFailure(call: Call<TeamsResponse>, t: Throwable) {
                    var error = "Unknown"
                    t.message?.let {
                        error = it
                    }
                    resultTeams.value = Resource.error(error,null)
                }
            })
        }
    }

    override fun getTeams(): LiveData<Resource<List<ITeamView>>> {
        return resultTeams
    }

    private fun setTeams(response: Response<TeamsResponse>) {
        if(response.body()!=null){
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

    private fun teamRequest(leagueName: String): TeamsRequest {
        val request = TeamsRequest()
        request.leagueName = leagueName
        return request
    }
}