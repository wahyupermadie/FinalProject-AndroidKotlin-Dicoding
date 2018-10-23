package com.example.wahyupermadi.latihanlayout.view.favorite

import android.content.Context
import com.example.wahyupermadi.latihanlayout.model.MatchItem

interface FavoriteContract{
    interface View{
        fun showFavoriteMatch(matchs : List<MatchItem>)
    }

    interface Presenter{
        fun getfavoriteMatch(context: Context)
    }
}