package com.example.wahyupermadi.latihanlayout.view.matchs.past

import com.example.wahyupermadi.latihanlayout.model.MatchItem

interface PastContract{
    interface View{
        fun showPastMatch(matchs : List<MatchItem>)
    }
    interface Presenter{
        fun getMatch()
    }
}