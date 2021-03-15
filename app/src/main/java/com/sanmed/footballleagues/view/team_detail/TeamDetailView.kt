package com.sanmed.footballleagues.view.team_detail

class TeamDetailView( private val _id: String,private val _name: String,
    private val _description: String, private val _foundation: String,
    private val _badge: String, private val _jersey: String,
    private val _links: String
) : ITeamDetailView {

    override fun getId(): String {
        return _id
    }

    override fun getName(): String {
        return _name
    }

    override fun getDescription(): String {
        return _description
    }

    override fun getFoundation(): String {
        return _foundation
    }

    override fun getBadge(): String {
        return _badge
    }

    override fun getJersey(): String {
        return _jersey
    }

    override fun getLinks(): String {
        return _links
    }
}