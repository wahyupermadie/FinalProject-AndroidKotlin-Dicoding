package com.example.wahyupermadi.latihanlayout.view.matchs.next

import com.example.wahyupermadi.latihanlayout.model.MatchItem

interface NextContract{
    interface View{
        fun showNextMatch(matchs : List<MatchItem>)
    }
    interface Presenter{
        fun getMatch()
    }
}