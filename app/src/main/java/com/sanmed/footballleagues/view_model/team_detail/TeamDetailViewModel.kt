package com.sanmed.footballleagues.view_model.team_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sanmed.footballleagues.model.repository.IFootballRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeamDetailViewModel @Inject constructor(private val mFootBalRepository: IFootballRepository) :
    ViewModel() {

    private val _teamDetailView = mFootBalRepository.getTeamDetail()
    private var _teamId: String = ""
    private val _name = Transformations.map(_teamDetailView) {
        it.data?.getName() ?: ""
    }
    private val _description = Transformations.map(_teamDetailView) { it.data?.getDescription() ?: "" }
    private val _foundation = Transformations.map(_teamDetailView) { it.data?.getFoundation() ?: "" }
    private val _badge = Transformations.map(_teamDetailView) { it.data?.getBadge() ?: "" }
    private val _jersey = Transformations.map(_teamDetailView) { it.data?.getJersey() ?: "" }
    private val _events =  MutableLiveData("No events.")
    private val _link = Transformations.map(_teamDetailView) { it.data?.getLinks() ?: "" }

    fun onTeamId(teamId: String) {
        if (_teamId != teamId) {
            _teamId = teamId
            mFootBalRepository.loadTeamDetail(teamId)
        }
    }

    fun getName(): LiveData<String> {
        return _name
    }

    fun getDescription(): LiveData<String> {
        return _description
    }

    fun getFoundation(): LiveData<String> {
        return _foundation
    }

    fun getBadge(): LiveData<String> {
        return _badge
    }

    fun getJersey(): LiveData<String> {
        return _jersey
    }

    fun getEvents(): LiveData<String> {
        return _events
    }

    fun getLink(): LiveData<String> {
        return _link
    }
}