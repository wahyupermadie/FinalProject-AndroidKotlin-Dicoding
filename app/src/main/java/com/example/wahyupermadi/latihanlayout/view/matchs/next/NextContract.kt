package com.example.wahyupermadi.latihanlayout.view.matchs.next

import com.example.wahyupermadi.latihanlayout.model.Leagues
import com.example.wahyupermadi.latihanlayout.model.MatchItem

interface NextContract{
    interface View{
        fun showNextMatch(matchs : List<MatchItem>)
        fun showLiga(liga : List<Leagues>)
        fun hideProgress()
        fun showProgress()
    }
    interface Presenter{
        fun getLiga()
        fun getMatch(id : String)
    }
}