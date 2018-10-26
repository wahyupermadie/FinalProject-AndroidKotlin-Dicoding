package com.example.wahyupermadi.latihanlayout.view.teams.detailTeam

import com.example.wahyupermadi.latihanlayout.model.TeamsItem

interface DetailTeamContract{
    interface View{
        fun showDeleted(text : String)
        fun showAdded(text : String)
        fun showState(teams : List<TeamsItem>)
    }
    interface Presenter{
        fun addFav(team : TeamsItem)
        fun removeFav(teamId : String)
        fun getState(id: String)
    }
}