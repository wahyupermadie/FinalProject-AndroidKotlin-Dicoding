package com.example.wahyupermadi.latihanlayout.view.teams.player

import com.example.wahyupermadi.latihanlayout.model.PlayerItem

interface PlayerContract{
    interface View{
        fun showProgress()
        fun hideProgress()
        fun showPlayer(player : List<PlayerItem>)
    }
    interface Presenter{
        fun getPlayer(id : String)
    }
}