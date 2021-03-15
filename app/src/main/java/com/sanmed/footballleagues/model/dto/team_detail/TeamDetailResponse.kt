package com.sanmed.footballleagues.model.dto.team_detail

import com.google.gson.annotations.SerializedName
import com.sanmed.footballleagues.model.dto.team.TeamDTO

class TeamDetailResponse {
    @SerializedName("teams")
    var teams:Array<TeamDetailDTO?> = Array(0){ TeamDetailDTO() }
}