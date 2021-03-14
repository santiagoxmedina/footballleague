package com.sanmed.footballleagues.view_model.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sanmed.footballleagues.model.remote.Resource
import com.sanmed.footballleagues.model.repository.IFootballRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val mFootBalRepository: IFootballRepository): ViewModel() {

    val navigateToLeagues: LiveData<Boolean>
        get() = Transformations.map(mFootBalRepository.preLoadData(),this::onPreloadDataResult)

    private fun onPreloadDataResult(result:Resource<Boolean>): Boolean {
        return result.data?:false
    }

}


