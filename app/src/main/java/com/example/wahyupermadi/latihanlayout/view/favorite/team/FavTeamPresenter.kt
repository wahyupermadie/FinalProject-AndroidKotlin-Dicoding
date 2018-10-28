package com.example.wahyupermadi.latihanlayout.view.favorite.team

import android.content.Context
import com.example.wahyupermadi.latihanlayout.db.database
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import com.example.wahyupermadi.latihanlayout.model.TeamsItem
import com.example.wahyupermadi.latihanlayout.view.favorite.team.FavTeamContract
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavTeamPresenter(val mView : FavTeamContract.View):
    FavTeamContract.Presenter {
    override fun getfavoriteTeam(context: Context) {
        context.database.use {
            val result = select(TeamsItem.TABLE_TEAM)
            val fav = result.parseList(classParser<TeamsItem>())
            mView.showFavoriteTeam(fav)
        }
    }
}