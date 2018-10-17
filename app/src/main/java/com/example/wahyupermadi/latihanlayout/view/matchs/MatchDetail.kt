package com.example.wahyupermadi.latihanlayout.view.matchs

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.R.drawable.ic_add_favorite
import com.example.wahyupermadi.latihanlayout.R.drawable.ic_added_favorite
import com.example.wahyupermadi.latihanlayout.R.id.add_to_favorite
import com.example.wahyupermadi.latihanlayout.R.menu.favorite_menu
import com.example.wahyupermadi.latihanlayout.api.ApiClient
import com.example.wahyupermadi.latihanlayout.api.ApiInterface
import com.example.wahyupermadi.latihanlayout.db.database
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import com.example.wahyupermadi.latihanlayout.model.TeamResponse
import com.example.wahyupermadi.latihanlayout.model.TeamsItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.ResourceSubscriber
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.toast
import java.sql.SQLClientInfoException

class MatchDetail : AppCompatActivity() {
    lateinit var matchs : MatchItem
    var homeTeam : MutableList<TeamsItem> = mutableListOf()
    var awayTeam : MutableList<TeamsItem> = mutableListOf()
    private var menuItem: Menu? = null
    private var isFavorite : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        matchs = intent.getParcelableExtra("match")
        getHomeImage(matchs.idHomeTeam)
        getAwayImage(matchs.idAwayTeam)

        tv_detail_match_date.text = matchs.dateEvent
        tv_home_detail_team_name.text = matchs.strHomeTeam
        tv_home_detail_score.text = matchs.intHomeScore
        tv_home_detail_deff.text = matchs.strHomeLineupDefense
        tv_home_detail_fw.text = matchs.strHomeLineupForward
        tv_home_detail_gk.text = matchs.strHomeLineupGoalkeeper
        tv_home_detail_goals.text = matchs.strHomeGoalDetails
        tv_home_detail_mf.text = matchs.strHomeLineupMidfield
        tv_home_detail_shots.text = matchs.intHomeShots
        tv_home_detail_sub.text = matchs.strHomeLineupSubstitutes
        tv_home_detail_team_formation.text = matchs.strHomeFormation

        tv_away_detail_team_name.text = matchs.strAwayTeam
        tv_away_detail_score.text = matchs.intAwayScore
        tv_away_detail_deff.text = matchs.strAwayLineupDefense
        tv_away_detail_fw.text = matchs.strAwayLineupForward
        tv_away_detail_gk.text = matchs.strAwayLineupGoalkeeper
        tv_away_detail_goals.text = matchs.strAwayGoalDetails
        tv_away_detail_mf.text = matchs.strAwayLineupMidfield
        tv_away_detail_shots.text = matchs.intAwayShots
        tv_away_detail_sub.text = matchs.strAwayLineupSubstitutes
        tv_away_detail_team_formation.text = matchs.strAwayFormation
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(favorite_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home -> {
                finish()
                true
            }

            add_to_favorite -> {
                if(isFavorite) removeFavorite() else addFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addFavorite() {
        try {
            database.use{
                insert(MatchItem.TABLE_MATCH,
                    MatchItem.ID_MATCH to matchs.idEvent,
                    MatchItem.AWAY_TEAM to matchs.strAwayTeam,
                    MatchItem.HOME_TEAM to matchs.strHomeTeam,
                    MatchItem.HOME_SCORE to matchs.intHomeScore,
                    MatchItem.AWAY_SCORE to matchs.intHomeScore,
                    MatchItem.EVENT_DATE to matchs.dateEvent)
            }
            toast("Successfully Added")
        }catch (e : SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

    private fun removeFavorite() {
        try {
            database.use{
                delete(MatchItem.TABLE_MATCH, "(ID_MATCH = {id})",
                    "id" to matchs.idEvent.toString())
            }
            toast("Remove Sukes")
        }catch (e: SQLClientInfoException){
            toast(""+e.printStackTrace())
        }
    }

    private fun setFavorite(){
        if(isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_favorite)
        }else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_favorite)
        }
    }

    fun getHomeImage(id : String?){
        val apiService = ApiClient.client?.create(ApiInterface::class.java)!!
        if (id != null) {
            apiService.getTeamDetail(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(object : ResourceSubscriber<TeamResponse>(){
                        override fun onComplete() {

                        }

                        override fun onNext(t: TeamResponse?) {
                            t?.teams?.let {
                                homeTeam.addAll(it)
                                Glide.with(ctx).load(homeTeam.get(0).strTeamBadge).into(iv_detail_home)
                            }

                        }

                        override fun onError(t: Throwable?) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                    })
        }
    }

    fun getAwayImage(id : String?){
        val apiService = ApiClient.client?.create(ApiInterface::class.java)!!
        if (id != null) {
            apiService.getTeamDetail(id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(object : ResourceSubscriber<TeamResponse>(){
                        override fun onComplete() {

                        }

                        override fun onNext(t: TeamResponse?) {
                            t?.teams?.let {
                                awayTeam.addAll(it)
                                Glide.with(ctx).load(awayTeam.get(0).strTeamBadge).into(iv_detail_away)
                            }

                        }

                        override fun onError(t: Throwable?) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                    })
        }
    }
}
