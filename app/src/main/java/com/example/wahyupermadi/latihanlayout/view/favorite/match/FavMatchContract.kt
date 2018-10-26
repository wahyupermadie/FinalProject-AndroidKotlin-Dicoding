package com.example.wahyupermadi.latihanlayout.view.favorite.match

import android.content.Context
import com.example.wahyupermadi.latihanlayout.model.MatchItem

interface FavMatchContract{
    interface View{
        fun showFavoriteMatch(matchs : List<MatchItem>)
    }

    interface Presenter{
        fun getfavoriteMatch(context: Context)
    }
}