package com.sanmed.footballleagues.view.team

import androidx.recyclerview.widget.DiffUtil

class TeamViewDiff : DiffUtil.ItemCallback<ITeamView?>() {

    override fun areItemsTheSame(oldItem: ITeamView, newItem: ITeamView): Boolean {
        return oldItem.getId() == newItem.getId()
    }

    override fun areContentsTheSame(oldItem: ITeamView, newItem: ITeamView): Boolean {
        return oldItem.getName() == newItem.getName() && oldItem.getStadium() == newItem.getStadium()
                && oldItem.getBadge() == newItem.getBadge()
    }

}