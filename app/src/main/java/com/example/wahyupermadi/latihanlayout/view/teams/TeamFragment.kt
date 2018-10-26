package com.example.wahyupermadi.latihanlayout.view.teams

import android.content.ContentValues.TAG
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.adapter.TeamAdapter
import com.example.wahyupermadi.latihanlayout.api.ApiClient
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.Leagues
import com.example.wahyupermadi.latihanlayout.model.LigaResponse
import com.example.wahyupermadi.latihanlayout.model.TeamsItem
import com.example.wahyupermadi.latihanlayout.utils.AppSchedulerProvider
import com.example.wahyupermadi.latihanlayout.view.teams.detailTeam.DetailTeamActivity
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.support.v4.ctx
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast


class TeamFragment : Fragment(), TeamContract.View{
    private lateinit var presenter: TeamPresenter
    private lateinit var rootView : View
    private var ligas : MutableList<Leagues> = mutableListOf()
    private var teams : MutableList<TeamsItem> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = layoutInflater.inflate(R.layout.fragment_team, container, false)
        return rootView
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = context?.getString(R.string.team_title)
        val apiInterface = ApiClient.client?.create(ApiInterface::class.java)
        val schedulers = AppSchedulerProvider()
        presenter = TeamPresenter(this,apiInterface!!,schedulers)
        presenter.getLiga()
    }

    override fun showLigas(liga: List<Leagues>) {
        ligas.clear()
        ligas.addAll(liga)
        setSpinner()
    }

    private fun setSpinner() {
        val arrayLigas : MutableList<String?> = mutableListOf()
        for((index,value) in ligas.withIndex()){
            arrayLigas.add(value.strLeague)
        }
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_dropdown_item_1line, arrayLigas)
        spinner_team.setAdapter(spinnerAdapter)
        spinner_team.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val ligaId = ligas[p2]
                if(ligaId.idLeague != null){
                    presenter.getTeam(ligaId.idLeague)
                }
            }
        }

        }

    override fun showTeam(team: List<TeamsItem>) {
        teams.clear()
        teams.addAll(team)
        val layoutManager = LinearLayoutManager(ctx,LinearLayoutManager.VERTICAL, false)
        rv_teams.layoutManager = layoutManager
        rv_teams.adapter = TeamAdapter(teams){
            startActivity<DetailTeamActivity>("teams" to it)
        }
    }

    override fun hidePrgress() {
        progress_teams.visibility = View.GONE
        rv_teams.visibility = View.VISIBLE
    }

    override fun showProgress() {
        progress_teams.visibility = View.VISIBLE
        rv_teams.visibility = View.GONE
    }
}