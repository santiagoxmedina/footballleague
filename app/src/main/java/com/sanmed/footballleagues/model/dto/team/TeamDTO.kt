package com.sanmed.footballleagues.model.dto.team

import com.google.gson.annotations.SerializedName

class TeamDTO {
    @SerializedName("idTeam")
    var teamId:String? = ""

    @SerializedName("strTeam")
    var name:String? = ""

    @SerializedName("strStadium")
    var stadium:String? = ""

    @SerializedName("strTeamBadge")
    var badge:String? = ""
}