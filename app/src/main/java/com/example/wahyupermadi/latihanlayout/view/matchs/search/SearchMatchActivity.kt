package com.example.wahyupermadi.latihanlayout.view.matchs.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.View
import android.widget.SearchView
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.adapter.PastMatchAdapter
import com.example.wahyupermadi.latihanlayout.api.ApiClient
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import com.example.wahyupermadi.latihanlayout.utils.AppSchedulerProvider
import com.example.wahyupermadi.latihanlayout.view.matchs.detail.DetailMatchActivity
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class SearchMatchActivity : AppCompatActivity(), SearchMatchContract.View{
    private lateinit var presenter: SearchMatchPresenter
    private var matchs : MutableList<MatchItem> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)
        supportActionBar?.title = "Search Match"
        val apiInterface = ApiClient.client?.create(ApiInterface::class.java)
        val scheduler = AppSchedulerProvider()
        presenter = SearchMatchPresenter(this, apiInterface!!, scheduler)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchView = menu?.findItem(R.id.search_menu)?.actionView as SearchView?
        searchView?.queryHint = getString(R.string.match_search)
        searchView?.isFocusable = true
        searchView?.setIconifiedByDefault(false)
        searchView?.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.getMatchByName(newText)
                return false
            }
        })
        return true
    }

    override fun hideProgress() {
        progress_search_match.visibility = View.GONE
        rv_search_match.visibility = View.VISIBLE
    }

    override fun showProgress() {
        progress_search_match.visibility = View.VISIBLE
        rv_search_match.visibility = View.GONE
    }

    override fun showMatchResult(match: List<MatchItem>) {
        matchs.clear()
        matchs.addAll(match)
        val layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        rv_search_match.layoutManager = layoutManager
        rv_search_match.adapter = PastMatchAdapter(matchs){
            startActivity<DetailMatchActivity>("match" to it)
        }
    }
}
