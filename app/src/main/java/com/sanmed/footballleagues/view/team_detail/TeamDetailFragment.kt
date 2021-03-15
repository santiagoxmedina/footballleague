package com.sanmed.footballleagues.view.team_detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sanmed.footballleagues.R
import com.sanmed.footballleagues.databinding.TeamDetailFragmentBinding
import com.sanmed.footballleagues.view_model.team_detail.TeamDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailFragment : Fragment() {

    private val args by navArgs<TeamDetailFragmentArgs>()
    private val mViewModel by viewModels<TeamDetailViewModel>()
    private lateinit var mBinding: TeamDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.team_detail_fragment, container, false)
        mBinding.lifecycleOwner = viewLifecycleOwner
        mBinding.viewModel = mViewModel
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.onTeamId(args.teamId)
    }

}