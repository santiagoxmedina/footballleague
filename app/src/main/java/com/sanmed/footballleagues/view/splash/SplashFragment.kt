package com.sanmed.footballleagues.view.splash

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.sanmed.footballleagues.R
import com.sanmed.footballleagues.view_model.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel


@AndroidEntryPoint
class SplashFragment : Fragment() {


    private  val mViewModel by viewModels<SplashViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSubscribers()
    }

    private fun initSubscribers() {
        mViewModel.navigateToLeagues.observe(viewLifecycleOwner,this::onNavigateToLeagues)
    }

    private fun onNavigateToLeagues(goToLeagues: Boolean) {
       if(goToLeagues){
           navigateToLeagues()
       }
    }

    private fun navigateToLeagues() {
        Navigation.findNavController(requireView()).navigate(SplashFragmentDirections.actionSplashFragmentToLeaguesFragment())
    }


}