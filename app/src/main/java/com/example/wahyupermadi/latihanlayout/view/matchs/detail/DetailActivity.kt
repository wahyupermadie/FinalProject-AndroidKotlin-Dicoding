package com.example.wahyupermadi.latihanlayout.view.matchs.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
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
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import java.sql.SQLClientInfoException

class DetailActivity : AppCompatActivity(),
    DetailContract.View {
    private var matchs : MatchItem? = null
    private var presenter : DetailPresenter? = null
    private var menuItem: Menu? = null
    private var isFavorite : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        matchs = intent.getParcelableExtra("match")
        checkFavorite()
        val apiInterface = ApiClient.client?.create(ApiInterface::class.java)
        presenter = apiInterface?.let {
            DetailPresenter(
                this, it
            )
        }
        presenter?.getAwayBadge(matchs?.idAwayTeam.toString())
        presenter?.getHomeBadge(matchs?.idHomeTeam.toString())

        tv_detail_match_date.text = matchs?.dateEvent
        tv_home_detail_team_name.text = matchs?.strHomeTeam
        tv_home_detail_score.text = matchs?.intHomeScore
        tv_home_detail_deff.text = matchs?.strHomeLineupDefense
        tv_home_detail_fw.text = matchs?.strHomeLineupForward
        tv_home_detail_gk.text = matchs?.strHomeLineupGoalkeeper
        tv_home_detail_goals.text = matchs?.strHomeGoalDetails
        tv_home_detail_mf.text = matchs?.strHomeLineupMidfield
        tv_home_detail_shots.text = matchs?.intHomeShots
        tv_home_detail_sub.text = matchs?.strHomeLineupSubstitutes
        tv_home_detail_team_formation.text = matchs?.strHomeFormation

        tv_away_detail_team_name.text = matchs?.strAwayTeam
        tv_away_detail_score.text = matchs?.intAwayScore
        tv_away_detail_deff.text = matchs?.strAwayLineupDefense
        tv_away_detail_fw.text = matchs?.strAwayLineupForward
        tv_away_detail_gk.text = matchs?.strAwayLineupGoalkeeper
        tv_away_detail_goals.text = matchs?.strAwayGoalDetails
        tv_away_detail_mf.text = matchs?.strAwayLineupMidfield
        tv_away_detail_shots.text = matchs?.intAwayShots
        tv_away_detail_sub.text = matchs?.strAwayLineupSubstitutes
        tv_away_detail_team_formation.text = matchs?.strAwayFormation
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
                    MatchItem.ID_MATCH to matchs?.idEvent,
                    MatchItem.ID_MATCH to matchs?.idEvent,
                    MatchItem.HOME_TEAM to matchs?.strHomeTeam,
                    MatchItem.AWAY_TEAM to matchs?.strAwayTeam,
                    MatchItem.EVENT_DATE to matchs?.dateEvent,
                    MatchItem.AWAY_SCORE to matchs?.intAwayScore,
                    MatchItem.HOME_SCORE to matchs?.intHomeScore,
                    MatchItem.AWAY_FORMATION to matchs?.strAwayFormation,
                    MatchItem.HOME_FORMATION to matchs?.strHomeFormation,
                    MatchItem.HOME_SHOTS to matchs?.intHomeShots,
                    MatchItem.AWAY_SHOTS to matchs?.intAwayShots,
                    MatchItem.HOME_GK to matchs?.strHomeLineupGoalkeeper,
                    MatchItem.HOME_DEFF to matchs?.strHomeLineupDefense,
                    MatchItem.HOME_FW to matchs?.strHomeLineupForward,
                    MatchItem.HOME_MF to matchs?.strHomeLineupMidfield,
                    MatchItem.HOME_SUBS to matchs?.strHomeLineupSubstitutes,
                    MatchItem.AWAY_GK to matchs?.strAwayLineupGoalkeeper,
                    MatchItem.AWAY_DEFF to matchs?.strAwayLineupDefense,
                    MatchItem.AWAY_FW to matchs?.strAwayLineupForward,
                    MatchItem.AWAY_MF to matchs?.strAwayLineupMidfield,
                    MatchItem.AWAY_SUBS to matchs?.strAwayLineupSubstitutes,
                    MatchItem.ID_HOME to matchs?.idHomeTeam,
                    MatchItem.ID_AWAY to matchs?.idAwayTeam,
                    MatchItem.AWAY_GOAL to matchs?.strAwayGoalDetails,
                    MatchItem.HOME_GOAL to matchs?.strHomeGoalDetails)
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
                    "id" to matchs?.idEvent.toString())
            }
            toast("Remove Sukes")
        }catch (e: SQLClientInfoException){
            toast(""+e.printStackTrace())
        }
    }

    private fun checkFavorite(){
        database.use {
            val match = select(MatchItem.TABLE_MATCH)
                .whereArgs("(ID_MATCH = {id})",
                    "id" to matchs?.idEvent.toString())
            val favorite = match.parseList(classParser<MatchItem>())
            if(!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun setFavorite(){
        if(isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_favorite)
        }else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_favorite)
        }
    }

    override fun showHomeBadge(imgHome: String) {
        Glide.with(ctx).load(imgHome).into(iv_detail_home)
    }

    override fun showAwayBadge(imgAway: String) {
        Glide.with(ctx).load(imgAway).into(iv_detail_away)
    }
}
