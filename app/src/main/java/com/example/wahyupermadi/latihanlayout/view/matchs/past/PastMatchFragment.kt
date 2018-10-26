package com.example.wahyupermadi.latihanlayout.view.matchs.past

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.adapter.PastMatchAdapter
import com.example.wahyupermadi.latihanlayout.api.ApiClient
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.Leagues
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import com.example.wahyupermadi.latihanlayout.utils.AppSchedulerProvider
import com.example.wahyupermadi.latihanlayout.view.matchs.detail.DetailActivity
import kotlinx.android.synthetic.main.past_fragment.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class PastMatchFragment : Fragment(), PastContract.View{
    private var pastMatchs : MutableList<MatchItem> = mutableListOf()
    private var ligas : MutableList<Leagues> = mutableListOf()
    private lateinit var presenter: PastPresenter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.past_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val apiService = ApiClient.client?.create(ApiInterface::class.java)
        val scheduler = AppSchedulerProvider()
        presenter = PastPresenter(this, apiService!!, scheduler)
        presenter.getLiga()

        sr_beforematch.setOnRefreshListener {
            sr_beforematch.isRefreshing = false
            setSpinner()
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

    override fun showLiga(liga: List<Leagues>) {
        ligas.clear()
        ligas.addAll(liga)
        setSpinner()
    }

    private fun setSpinner() {
        val ligasList : MutableList<String> = mutableListOf()
        for((index, value) in ligas.withIndex()){
            value.strLeague?.let { ligasList.add(it) }
        }
        val spinnerAdapter = ArrayAdapter(ctx,R.layout.support_simple_spinner_dropdown_item, ligasList)
        spinner_past.adapter = spinnerAdapter
        spinner_past.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val ligaId = ligas[p2]
                if(ligaId.idLeague != null){
                    presenter.getMatch(ligaId.idLeague)
                }
            }

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