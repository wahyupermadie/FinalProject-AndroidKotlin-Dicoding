package com.example.wahyupermadi.latihanlayout.view.favorite.match

import android.content.Context
import com.example.wahyupermadi.latihanlayout.db.database
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavMatchPresenter(val mView : FavMatchContract.View):
    FavMatchContract.Presenter {
    override fun getfavoriteMatch(context: Context) {
        context.database.use {
            val result = select(MatchItem.TABLE_MATCH)
            val fav = result.parseList(classParser<MatchItem>())
            mView.showFavoriteMatch(fav)
        }
    }
}