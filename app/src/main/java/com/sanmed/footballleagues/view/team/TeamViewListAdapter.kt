package com.sanmed.footballleagues.view.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.sanmed.footballleagues.R
import com.sanmed.footballleagues.databinding.TeamViewBinding
import com.sanmed.footballleagues.view.action.ActionClick

class TeamViewListAdapter(diff:TeamViewDiff,action:ActionClick<ITeamView>) : ListAdapter<ITeamView?, TeamViewViewHolder>(diff) {

    private val mAction = action;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewViewHolder {
        val binding = DataBindingUtil.inflate<TeamViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.team_view, parent, false)
        binding.action = mAction
        return TeamViewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}