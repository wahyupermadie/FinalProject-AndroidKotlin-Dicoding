package com.example.wahyupermadi.latihanlayout.view.favorite.team

import android.content.Context
import com.example.wahyupermadi.latihanlayout.model.TeamsItem

interface FavTeamContract{
    interface View{
        fun showFavoriteTeam(teams : List<TeamsItem>)
    }

    interface Presenter{
        fun getfavoriteTeam(context: Context)
    }
}