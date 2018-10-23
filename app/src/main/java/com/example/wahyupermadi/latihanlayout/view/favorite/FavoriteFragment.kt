package com.example.wahyupermadi.latihanlayout.view.favorite


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.adapter.FavoriteAdapter
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import com.example.wahyupermadi.latihanlayout.view.matchs.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity


class FavoriteFragment : Fragment(), FavoriteContract.View {
    private var favorite : MutableList<MatchItem> = mutableListOf()
    lateinit var presenter : FavoritePresenter
    var rootView : View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Favorite Match"
        rootView = inflater.inflate(R.layout.fragment_favorite, container, false)
        return rootView
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = FavoritePresenter(this)
        presenter.getfavoriteMatch(ctx)
        sr_favorite.setOnRefreshListener {
            sr_favorite.isRefreshing = false
            presenter.getfavoriteMatch(ctx)
        }

    }

    override fun showFavoriteMatch(matchs: List<MatchItem>) {
        favorite.clear()
        favorite.addAll(matchs)
        rv_favorite.layoutManager = LinearLayoutManager(context)
        rv_favorite.adapter = FavoriteAdapter(favorite){
            startActivity<DetailActivity>("match" to it)
        }
    }
}
