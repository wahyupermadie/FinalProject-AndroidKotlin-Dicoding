package com.example.wahyupermadi.latihanlayout.view.favorite.team

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.adapter.TeamAdapter
import com.example.wahyupermadi.latihanlayout.model.TeamsItem
import com.example.wahyupermadi.latihanlayout.view.teams.detailTeam.DetailTeamActivity
import kotlinx.android.synthetic.main.team_favorite_fragment.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class FavTeamFragment : Fragment(), FavTeamContract.View{
    private var team : MutableList<TeamsItem> = mutableListOf()

    private lateinit var presenter: FavTeamPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.team_favorite_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = FavTeamPresenter(this)
        presenter.getfavoriteTeam(ctx)
        swipe_fav_team.setOnRefreshListener {
            swipe_fav_team.isRefreshing = false
            presenter.getfavoriteTeam(ctx)
        }
    }

    override fun showFavoriteTeam(teams: List<TeamsItem>) {
        team.clear()
        team.addAll(teams)
        val layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL, false)
        rv_fav_team.layoutManager = layoutManager
        rv_fav_team.adapter = TeamAdapter(team){
            startActivity<DetailTeamActivity>("teams" to it)
        }
    }
}