package com.sanmed.footballleagues.view.team

class TeamView( id:String,name:String,stadium:String,badge:String):ITeamView {

    private val _name = name;
    private val _stadium= stadium;
    private val _badge = badge;
    private val _id = id;

    override fun getName(): String {
        return _name
    }

    override fun getStadium(): String {
        return _stadium
    }

    override fun getBadge(): String {
        return _badge
    }

    override fun getId(): String {
        return _id
    }
}