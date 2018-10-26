package com.example.wahyupermadi.latihanlayout.view.teams

import com.example.wahyupermadi.latihanlayout.model.Leagues
import com.example.wahyupermadi.latihanlayout.model.TeamsItem

interface TeamContract{
    interface View{
        fun showLigas(liga : List<Leagues>)
        fun showTeam(team : List<TeamsItem>)
        fun hidePrgress()
        fun showProgress()
    }
    interface Presenter{
        fun getLiga()
        fun getTeam(id : String)
    }
}