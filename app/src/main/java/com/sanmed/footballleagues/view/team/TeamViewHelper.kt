package com.sanmed.footballleagues.view.team

import androidx.recyclerview.widget.RecyclerView
import com.sanmed.footballleagues.model.dto.TeamDTO
import com.sanmed.footballleagues.model.entities.TeamEntity
import com.sanmed.footballleagues.view.action.ActionClick

class TeamViewHelper {
    companion object {
        fun getListAdapter(action: ActionClick<ITeamView>): TeamViewListAdapter {
            val adapter = TeamViewListAdapter(TeamViewDiff(),action)
            return adapter
        }

        fun parseTeamViewFromTeamEntity(teamEntity: TeamEntity): ITeamView {
            return TeamView(teamEntity.teamId,teamEntity.teamName,teamEntity.teamStadium,teamEntity.teamBadge)
        }

        fun parseTeamViewFromTeamDTO(teamDTO: TeamDTO): ITeamView {
            return TeamView(teamDTO.teamId?:"",teamDTO.name?:"",teamDTO.stadium?:"",teamDTO.badge?:"")
        }

    }
}