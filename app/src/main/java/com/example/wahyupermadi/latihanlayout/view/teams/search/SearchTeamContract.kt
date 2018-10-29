package com.example.wahyupermadi.latihanlayout.view.teams.search

import com.example.wahyupermadi.latihanlayout.model.TeamsItem

interface SearchTeamContract{
    interface View{
        fun hideProgress()
        fun showProgress()
        fun showTeamResult(team : List<TeamsItem>)
    }
    interface Presenter{
        fun getTeamByName(name : String)
    }
}