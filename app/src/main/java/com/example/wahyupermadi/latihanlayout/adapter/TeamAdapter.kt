package com.example.wahyupermadi.latihanlayout.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.model.TeamsItem
import kotlinx.android.synthetic.main.team_list.view.*

class TeamAdapter(private val teamsItem: List<TeamsItem>, private val listener : (TeamsItem) -> Unit):RecyclerView.Adapter<TeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TeamViewHolder {
        return TeamViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.team_list, parent, false))
    }

    override fun getItemCount(): Int {
        return teamsItem.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, p1: Int) {
        holder.bindView(teamsItem[p1], listener)
    }

}

class TeamViewHolder(view:View) : RecyclerView.ViewHolder(view) {
    fun bindView(
        teamsItem: TeamsItem,
        listener: (TeamsItem) -> Unit
    ) {
        itemView.tv_list_team_name.text = teamsItem.strTeam
        Glide.with(itemView.context).load(teamsItem.strTeamBadge).into(itemView.iv_list_team)
        itemView.setOnClickListener {
            listener(teamsItem)
        }
    }
}
