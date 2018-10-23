package com.example.wahyupermadi.latihanlayout.view.matchs.past

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.adapter.PastMatchAdapter
import com.example.wahyupermadi.latihanlayout.api.ApiClient
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import com.example.wahyupermadi.latihanlayout.utils.AppSchedulerProvider
import com.example.wahyupermadi.latihanlayout.view.matchs.detail.DetailActivity
import kotlinx.android.synthetic.main.past_fragment.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class PastMatchFragment : Fragment(), PastContract.View{
    private var pastMatchs : MutableList<MatchItem> = mutableListOf()
    lateinit var presenter: PastPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.past_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val apiService = ApiClient.client?.create(ApiInterface::class.java)
        val scheduler = AppSchedulerProvider()
        presenter = PastPresenter(this, apiService!!, scheduler)
        presenter.getMatch()

        sr_beforematch.setOnRefreshListener {
            sr_beforematch.isRefreshing = false
            presenter.getMatch()
        }
    }

    override fun showPastMatch(matchs: List<MatchItem>) {
        pastMatchs.clear()
        pastMatchs.addAll(matchs)
        val layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        rv_before.layoutManager = layoutManager
        rv_before.adapter = PastMatchAdapter(pastMatchs){
            startActivity<DetailActivity>("match" to it)
        }
    }

    override fun hideProgress() {
        progress_before.visibility = View.GONE
        rv_before.visibility = View.VISIBLE
    }

    override fun showProgress() {
        progress_before.visibility = View.VISIBLE
        rv_before.visibility = View.GONE
    }
}