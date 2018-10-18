package com.example.wahyupermadi.latihanlayout.view.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.adapter.FavoriteAdapter
import com.example.wahyupermadi.latihanlayout.db.database
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import com.example.wahyupermadi.latihanlayout.view.matchs.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity


class FavoriteFragment : Fragment() {

    private var favorite : MutableList<MatchItem> = mutableListOf()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Favorite Match"
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swipeRefreshLayout = sr_favorite
        swipeRefreshLayout.setOnRefreshListener(object: SwipeRefreshLayout.OnRefreshListener{
            override fun onRefresh() {
                showFavorite()
            }
        })

        showFavorite()
    }

    private fun showFavorite(){
        context?.database?.use {
            swipeRefreshLayout.isRefreshing = false
            val result = select(MatchItem.TABLE_MATCH)
            val fav = result.parseList(classParser<MatchItem>())
            favorite.clear()
            favorite.addAll(fav)
            rv_favorite.layoutManager = LinearLayoutManager(context)
            rv_favorite.adapter = FavoriteAdapter(favorite){
                startActivity<DetailActivity>("match" to it)
            }
        }
    }

}
