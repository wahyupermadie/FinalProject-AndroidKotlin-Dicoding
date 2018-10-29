package com.example.wahyupermadi.latihanlayout.view.matchs.search

import com.example.wahyupermadi.latihanlayout.model.MatchItem

interface SearchMatchContract{
    interface View{
        fun hideProgress()
        fun showProgress()
        fun showMatchResult(match : List<MatchItem>)
    }
    interface Presenter{
        fun getMatchByName(name : String)
    }
}