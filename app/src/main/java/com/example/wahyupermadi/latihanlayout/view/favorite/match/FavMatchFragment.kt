package com.example.wahyupermadi.latihanlayout.view.favorite.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.adapter.PastMatchAdapter
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import com.example.wahyupermadi.latihanlayout.view.favorite.team.FavTeamContract
import com.example.wahyupermadi.latihanlayout.view.favorite.team.FavTeamPresenter
import com.example.wahyupermadi.latihanlayout.view.matchs.detail.DetailMatchActivity
import kotlinx.android.synthetic.main.match_favorite_fragment.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class FavMatchFragment : Fragment(), FavMatchContract.View{
    private var match : MutableList<MatchItem> = mutableListOf()

    private lateinit var presenter: FavMatchPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.match_favorite_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = FavMatchPresenter(this)
        presenter.getfavoriteMatch(ctx)

        swipe_fav_match.setOnRefreshListener {
            swipe_fav_match.isRefreshing = false
            presenter.getfavoriteMatch(ctx)
        }
    }

    override fun showFavoriteMatch(matchs: List<MatchItem>) {
        match.clear()
        match.addAll(matchs)
        val layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL, false)
        rv_fav_match.layoutManager = layoutManager
        rv_fav_match.adapter = PastMatchAdapter(match){
            startActivity<DetailMatchActivity>("match" to it)
        }
    }


}