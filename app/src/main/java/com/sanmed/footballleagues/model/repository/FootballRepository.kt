package com.sanmed.footballleagues.model.repository

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.sanmed.footballleagues.model.datasource.ILocalDataSource
import com.sanmed.footballleagues.model.datasource.IRemoteDataSource
import com.sanmed.footballleagues.model.remote.Resource
import com.sanmed.footballleagues.view.team.ITeamView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class FootballRepository @Inject constructor(
    remoteDataSource: IRemoteDataSource,
    localDataSource: ILocalDataSource
) : IFootballRepository {

    private val mRemoteDataSource = remoteDataSource
    private val mLocalDataSource = localDataSource
    private val mTeams = MediatorLiveData<Resource<List<ITeamView>>>()
    private val mPreload = MediatorLiveData<Resource<Boolean>>()

    init {
        mTeams.value = Resource.loading(null)
        mTeams.addSource(mLocalDataSource.getTeams()) { newData ->
            setTeamsValue(Resource.success(newData))
        }
    }

    @MainThread
    private fun setTeamsValue(newValue: Resource<List<ITeamView>>) {
        if (mTeams.value != newValue) {
            mTeams.value = newValue
        }
    }

    override fun preLoadData(): LiveData<Resource<Boolean>> {
        GlobalScope.launch {
            delay(2000L)
            mPreload.postValue(Resource.success(true))
        }
        return mPreload;
    }

    override fun getTeams(): LiveData<Resource<List<ITeamView>>> {
        return mTeams
    }

    override fun loadTeams(leagueName: String) {
        mRemoteDataSource.loadTeams(leagueName);
    }


}