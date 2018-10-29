package com.example.wahyupermadi.latihanlayout.view.matchs.detail

interface DetailMatchContract {

    interface View{
        fun showHomeBadge(imgHome : String)
        fun showAwayBadge(imgAway : String)
    }

    interface Presenter{
        fun getHomeBadge(id :String)
        fun getAwayBadge(id :String)
    }
}