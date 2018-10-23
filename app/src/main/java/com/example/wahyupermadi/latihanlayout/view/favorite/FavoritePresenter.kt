package com.example.wahyupermadi.latihanlayout.view.favorite

import android.content.Context
import com.example.wahyupermadi.latihanlayout.db.database
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoritePresenter(val mView : FavoriteContract.View):FavoriteContract.Presenter{
    override fun getfavoriteMatch(context: Context) {
        context.database.use {
            val result = select(MatchItem.TABLE_MATCH)
            val fav = result.parseList(classParser<MatchItem>())
            mView.showFavoriteMatch(fav)
        }
    }
}