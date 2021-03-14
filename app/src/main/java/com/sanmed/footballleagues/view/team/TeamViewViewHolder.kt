package com.sanmed.footballleagues.view.team

import androidx.recyclerview.widget.RecyclerView
import com.sanmed.footballleagues.databinding.TeamViewBinding

class TeamViewViewHolder (binding: TeamViewBinding) : RecyclerView.ViewHolder(binding.root) {

    private val mBinding = binding;

    fun bind(itemView: ITeamView?) {
        itemView.let {
            mBinding.team = it
            mBinding.executePendingBindings()
        }
    }

}