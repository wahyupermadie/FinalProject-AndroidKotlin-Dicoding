package com.example.wahyupermadi.latihanlayout.view.matchs.next

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.adapter.NextMatchAdapter
import com.example.wahyupermadi.latihanlayout.api.ApiClient
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.model.Leagues
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import com.example.wahyupermadi.latihanlayout.utils.AppSchedulerProvider
import com.example.wahyupermadi.latihanlayout.view.matchs.detail.DetailMatchActivity
import kotlinx.android.synthetic.main.next_fragment.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class NextMatchFragment: Fragment(), NextContract.View{
    private var nextMatchs : MutableList<MatchItem> = mutableListOf()
    lateinit var presenter: NextPresenter
    private var ligas : MutableList<Leagues> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.next_fragment, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val apiService = ApiClient.client?.create(ApiInterface::class.java)
        val scheduler = AppSchedulerProvider()
        presenter = NextPresenter(this, apiService!!, scheduler)
        presenter.getLiga()
        sr_nextmatch.setOnRefreshListener {
            sr_nextmatch.isRefreshing = false
            setSpinner()
        }
    }

    override fun hideProgress() {
        progress_next.visibility = View.GONE
        rv_next.visibility = View.VISIBLE
    }

    override fun showLiga(liga: List<Leagues>) {
        ligas.clear()
        ligas.addAll(liga)
        setSpinner()
    }

    private fun setSpinner() {
        val ligaItem : MutableList<String> = mutableListOf()
        for ((index, values) in ligas.withIndex()){
            values.strLeague?.let { ligaItem.add(it) }
        }
        val spinnerAdapter = ArrayAdapter(ctx,R.layout.support_simple_spinner_dropdown_item,ligaItem)
        spinner_next.adapter = spinnerAdapter
        spinner_next.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
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

    override fun showProgress() {
        progress_next.visibility = View.VISIBLE
        rv_next.visibility = View.GONE
    }

    override fun showNextMatch(matchs: List<MatchItem>) {
        nextMatchs.clear()
        nextMatchs.addAll(matchs)
        val layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
        rv_next.layoutManager = layoutManager
        rv_next.adapter = NextMatchAdapter(nextMatchs){
            startActivity<DetailMatchActivity>("match" to it)
        }
    }
}