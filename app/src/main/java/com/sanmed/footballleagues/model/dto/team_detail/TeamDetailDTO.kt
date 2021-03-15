package com.sanmed.footballleagues.model.dto.team_detail

import com.google.gson.annotations.SerializedName

class TeamDetailDTO {
    @SerializedName("idTeam")
    var teamId:String? = ""

    @SerializedName("strTeam")
    var name:String? = ""

    @SerializedName("strDescriptionEN")
    var descriptionEn:String? = ""

    @SerializedName("intFormedYear")
    var foundation:String? = ""

    @SerializedName("strTeamBadge")
    var badge:String? = ""

    @SerializedName("strTeamJersey")
    var jersey:String? = ""

    @SerializedName("strWebsite")
    var webSite:String? = ""

    @SerializedName("strFacebook")
    var faceBook:String? = ""

    @SerializedName("strTwitter")
    var twitter:String? = ""

    @SerializedName("strInstagram")
    var instagram:String? = ""
}