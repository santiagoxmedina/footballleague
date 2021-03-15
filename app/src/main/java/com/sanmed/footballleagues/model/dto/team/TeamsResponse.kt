package com.sanmed.footballleagues.model.dto.team

import com.google.gson.annotations.SerializedName

class TeamsResponse {

    @SerializedName("teams")
    var teams:Array<TeamDTO?> = Array(0){ TeamDTO() }
}