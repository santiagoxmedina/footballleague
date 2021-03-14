package com.sanmed.footballleagues.view_model.leagues

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sanmed.footballleagues.model.remote.Resource
import com.sanmed.footballleagues.model.repository.IFootballRepository
import com.sanmed.footballleagues.view.team.ITeamView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LeaguesViewModel @Inject constructor(private val mFootBalRepository: IFootballRepository) :
    ViewModel() {

    private val _navigateToTeamDetail = MutableLiveData<Boolean>()
    val navigateToTeamDetail: LiveData<Boolean>
        get() = _navigateToTeamDetail

    private val _leagueNameData = MutableLiveData<String>()
    private val leagueNameData: LiveData<String>
        get() = _leagueNameData

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private var _teamSelectedId = ""

    init {
        _leagueNameData.value = "Spanish La Liga"
        mFootBalRepository.loadTeams(_leagueNameData.value!!)
    }

    fun getTeamSelectedId(): String {
        return _teamSelectedId
    }

    fun onTeamSelected(team: ITeamView) {
        _teamSelectedId = team.getId()
        _navigateToTeamDetail.value = true
    }

    fun getTeams(): LiveData<List<ITeamView>> {
        return Transformations.map(mFootBalRepository.getTeams(),this::onGetTeams)
    }

    private fun onGetTeams(result:Resource<List<ITeamView>> ): List<ITeamView> {
        return if(result.data!=null){
            result.data
        }else{
            result.message?.let {
                _message.value = it
            }
            emptyList()
        }
    }

    fun getLeagueName(): LiveData<String> {
        return leagueNameData
    }

    fun onNavigateToTeamDetailCompleted() {
        _navigateToTeamDetail.value = false
    }

    fun onMessageCompleted() {
        _message.value = ""
    }
}