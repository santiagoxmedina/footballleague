package com.sanmed.footballleagues.view.team_detail

import androidx.lifecycle.LiveData

interface ITeamDetailView {
    fun getId(): String;
    fun getName(): String;
    fun getDescription(): String;
    fun getFoundation():  String;
    fun getBadge(): String;
    fun getJersey(): String;
    fun getLinks():  String;
}