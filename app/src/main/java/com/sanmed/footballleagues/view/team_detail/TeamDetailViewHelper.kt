package com.sanmed.footballleagues.view.team_detail

import com.sanmed.footballleagues.model.dto.team.TeamDTO
import com.sanmed.footballleagues.model.dto.team_detail.TeamDetailDTO
import com.sanmed.footballleagues.model.entities.TeamDetailEntity
import com.sanmed.footballleagues.model.entities.TeamEntity
import com.sanmed.footballleagues.view.action.ActionClick
import com.sanmed.footballleagues.view.team.ITeamView
import com.sanmed.footballleagues.view.team.TeamView
import com.sanmed.footballleagues.view.team.TeamViewDiff
import com.sanmed.footballleagues.view.team.TeamViewListAdapter
import java.lang.StringBuilder

class TeamDetailViewHelper {
    companion object {

        fun parseTeamDetailViewFromTeamDetailEntity(teamDetailEntity: TeamDetailEntity): ITeamDetailView {
            return TeamDetailView(teamDetailEntity.teamId,teamDetailEntity.name,teamDetailEntity.description,teamDetailEntity.foundation,teamDetailEntity.badge,teamDetailEntity.jersey,teamDetailEntity.links)
        }

        fun parseTeamDetailViewFromTeamDTO(teamDetailDTO: TeamDetailDTO): ITeamDetailView {
            return TeamDetailView(teamDetailDTO.teamId?:"",teamDetailDTO.name?:"",getLanguageDescription(teamDetailDTO),teamDetailDTO.foundation?:"",teamDetailDTO.badge?:"",teamDetailDTO.jersey?:"",getLinks(teamDetailDTO))
        }

        private fun getLinks(teamDetailDTO: TeamDetailDTO): String {
            return StringBuilder().append(teamDetailDTO.webSite)
                .append("\n").append(teamDetailDTO.faceBook)
                .append("\n").append(teamDetailDTO.twitter)
                .append("\n").append(teamDetailDTO.instagram)
                .toString()
        }

        private fun getLanguageDescription(teamDetailDTO: TeamDetailDTO): String {
            return teamDetailDTO.descriptionEn?:""
        }

        fun parseTeamDetailEntityFromTeamDetailView(teamDetailView: ITeamDetailView): TeamDetailEntity {
            return TeamDetailEntity(teamDetailView.getId(),teamDetailView.getName(),teamDetailView.getDescription(),teamDetailView.getFoundation(),teamDetailView.getBadge(),teamDetailView.getJersey(),teamDetailView.getLinks())
        }

    }
}