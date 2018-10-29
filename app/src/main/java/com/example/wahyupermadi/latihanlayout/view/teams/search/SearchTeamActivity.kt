package com.example.wahyupermadi.latihanlayout.view.teams.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.View
import android.widget.SearchView
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.adapter.TeamAdapter
import com.example.wahyupermadi.latihanlayout.api.ApiClient
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.TeamsItem
import com.example.wahyupermadi.latihanlayout.utils.AppSchedulerProvider
import com.example.wahyupermadi.latihanlayout.view.teams.detailTeam.DetailTeamActivity
import kotlinx.android.synthetic.main.activity_search_team.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity

class SearchTeamActivity : AppCompatActivity(), SearchTeamContract.View {
    private lateinit var presenter: SearchTeamPresenter
    private var teams : MutableList<TeamsItem> = mutableListOf()
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)
        supportActionBar?.title = "Search Team"
        val apiInterface = ApiClient.client?.create(ApiInterface::class.java)
        val scheduler = AppSchedulerProvider()
        presenter = SearchTeamPresenter(this, apiInterface!!, scheduler)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.search_menu)?.actionView as SearchView?
        searchView?.queryHint = getString(R.string.team_Search)
        searchView?.isFocusable = true
        searchView?.setIconifiedByDefault(false)
        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.getTeamByName(newText)
                return false
            }
        })
        return true
    }
    override fun hideProgress() {
        progress_search_team.visibility = View.GONE
        rv_search_team.visibility = View.VISIBLE
    }

    override fun showProgress() {
        progress_search_team.visibility = View.VISIBLE
        rv_search_team.visibility = View.GONE
    }

    override fun showTeamResult(team: List<TeamsItem>) {
        teams.clear()
        teams.addAll(team)
        val layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        rv_search_team.layoutManager = layoutManager
        rv_search_team.adapter = TeamAdapter(teams){
            startActivity<DetailTeamActivity>("teams" to it)
        }
    }
}
