package com.sanmed.footballleagues.view.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sanmed.footballleagues.view.action.ActionClick
import com.sanmed.footballleagues.view.loader.ImageLoader
import com.sanmed.footballleagues.view.team.ITeamView
import com.sanmed.footballleagues.view.team.TeamClick
import com.sanmed.footballleagues.view.team.TeamViewHelper
import com.sanmed.footballleagues.view.team.TeamViewListAdapter

object BindingAdapter {
    @BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String?, placeholder: Drawable) {
        if (url == null || url.isEmpty()) {
            imageView.setImageDrawable(placeholder);
        } else {
            ImageLoader.loadInto(imageView, url, placeholder);
        }
    }

    @BindingAdapter(value = ["teams", "action"], requireAll = false)
    @JvmStatic
    fun setTeams(view: RecyclerView, teams: List<ITeamView>, action: TeamClick) {
        lateinit var teamViewListAdapter: TeamViewListAdapter
        if (view.adapter == null) {
            teamViewListAdapter = TeamViewHelper.getListAdapter(action)
            view.adapter = teamViewListAdapter
        } else {
            teamViewListAdapter = view.adapter as TeamViewListAdapter
        }


        if (teams.isNotEmpty()) {
            teamViewListAdapter.submitList(teams)
        } else {
            teamViewListAdapter.submitList(emptyList())
        }
    }
}