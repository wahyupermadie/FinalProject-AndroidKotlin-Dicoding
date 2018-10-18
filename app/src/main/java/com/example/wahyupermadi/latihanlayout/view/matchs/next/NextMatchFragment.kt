package com.example.wahyupermadi.latihanlayout.view.matchs.next

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.adapter.NextMatchAdapter
import com.example.wahyupermadi.latihanlayout.api.ApiClient
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import com.example.wahyupermadi.latihanlayout.view.matchs.detail.DetailActivity
import kotlinx.android.synthetic.main.next_fragment.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class NextMatchFragment: Fragment(), NextContract.View{
    var nextMatchs : MutableList<MatchItem> = mutableListOf()
    lateinit var presenter: NextPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Next Match"
        return inflater.inflate(R.layout.next_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val apiService = ApiClient.client?.create(ApiInterface::class.java)

        presenter = NextPresenter(this, apiService!!)
        presenter.getMatch()

        sr_nextmatch.setOnRefreshListener {
            sr_nextmatch.isRefreshing = false
            presenter.getMatch()
        }
    }

    override fun showNextMatch(matchs: List<MatchItem>) {
        nextMatchs.clear()
        nextMatchs.addAll(matchs)
        val layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        rv_next.layoutManager = layoutManager
        rv_next.adapter = NextMatchAdapter(nextMatchs){
            startActivity<DetailActivity>("match" to it)
        }
    }
    companion object {
        fun newInstance() : NextMatchFragment = NextMatchFragment()
    }
}