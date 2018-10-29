package com.example.wahyupermadi.latihanlayout.view.teams.player

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.adapter.PlayerAdapter
import com.example.wahyupermadi.latihanlayout.api.ApiClient
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.PlayerItem
import com.example.wahyupermadi.latihanlayout.model.TeamsItem
import com.example.wahyupermadi.latihanlayout.utils.AppSchedulerProvider
import com.example.wahyupermadi.latihanlayout.view.teams.detailPlayer.DetailPlayerActivity
import kotlinx.android.synthetic.main.fragment_player.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class PlayerFragment : Fragment(), PlayerContract.View{
    private lateinit var presenter: PlayerPresenter
    private var players : MutableList<PlayerItem> = mutableListOf()
    private var team : TeamsItem? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val scheduler = AppSchedulerProvider()
        val apiInterface = ApiClient.client?.create(ApiInterface::class.java)
        presenter = PlayerPresenter(this,scheduler, apiInterface!!)
        val bundle = arguments
        team = bundle?.getParcelable("teams")
        presenter.getPlayer(team?.idTeam.toString())
    }

    override fun showProgress() {
        progress_player.visibility = View.VISIBLE
        rv_player.visibility = View.GONE
    }

    override fun hideProgress() {
        progress_player.visibility = View.GONE
        rv_player.visibility = View.VISIBLE
    }

    override fun showPlayer(player: List<PlayerItem>) {
        players.clear()
        players.addAll(player)
        val layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL, false)
        rv_player.layoutManager = layoutManager
        rv_player.adapter = PlayerAdapter(players){
            startActivity<DetailPlayerActivity>("player" to it)
        }
    }
}