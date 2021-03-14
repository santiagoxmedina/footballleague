package com.sanmed.footballleagues.view.leagues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.sanmed.footballleagues.R
import com.sanmed.footballleagues.databinding.LeaguesFragmentBinding
import com.sanmed.footballleagues.view_model.leagues.LeaguesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaguesFragment : Fragment() {

    private val mViewModel by viewModels<LeaguesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val mBinding:LeaguesFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.leagues_fragment, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.viewModel = mViewModel
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSubscribers()
    }

    private fun initSubscribers() {
        mViewModel.navigateToTeamDetail.observe(viewLifecycleOwner,this::onNavigateToTeamDetail)
        mViewModel.message.observe(viewLifecycleOwner,this::onMessage)
    }

    private fun onMessage(message:String) {
        if(message.isNotEmpty()){
            Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
            mViewModel.onMessageCompleted()
        }
    }

    private fun onNavigateToTeamDetail(goToTeamDetail:Boolean) {
        if(goToTeamDetail){
            navigateToTeamDetail(mViewModel.getTeamSelectedId())
            mViewModel.onNavigateToTeamDetailCompleted()
        }
    }

    private fun navigateToTeamDetail(teamSelectedId: String) {
        Navigation.findNavController(requireView()).navigate(LeaguesFragmentDirections.actionLeaguesFragmentToTeamDetailFragment(teamSelectedId))
    }

}