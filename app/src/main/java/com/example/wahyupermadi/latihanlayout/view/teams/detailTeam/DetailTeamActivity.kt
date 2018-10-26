package com.example.wahyupermadi.latihanlayout.view.teams.detailTeam

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.model.TeamsItem
import com.example.wahyupermadi.latihanlayout.view.teams.overview.OverviewFragment
import com.example.wahyupermadi.latihanlayout.view.teams.player.PlayerFragment
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.design.snackbar

class DetailTeamActivity : AppCompatActivity(), DetailTeamContract.View {
    private lateinit var teams : TeamsItem
    private var menuItem : Menu? = null
    lateinit var view: View
    private var isFavorite : Boolean = false
    lateinit var presenter: DetailTeamPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        view = findViewById(android.R.id.content)

        teams = intent.getParcelableExtra("teams")
        supportActionBar?.title = teams.strTeam
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = DetailTeamPresenter(this, ctx)
        teams.idTeam?.let { presenter.getState(it) }

        val detailAdapter = DetailAdapter(supportFragmentManager, teams)
        viewpager_team.adapter = detailAdapter
        tab_team.setupWithViewPager(viewpager_team)

        setData()
    }

    private fun setData() {
        Glide.with(ctx).load(teams.strTeamBadge).into(iv_team_detail)
        tv_detail_team_name.text = teams.strTeam
        tv_team_detail_stadion.text = teams.strStadium
        tv_detail_team_year.text = teams.intFormedYear.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    private fun setFavorite() {
        if (isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_favorite)
        }
        else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_favorite)
        }
    }
    private fun showSnack(view: View, text : String){
        val snackbar = Snackbar.make(view, text,
            Snackbar.LENGTH_LONG).setAction("Action", null)
        snackbar.setActionTextColor(Color.BLUE)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.GREEN)
        snackbar.show()
    }
    override fun showDeleted(text: String) {
        showSnack(view, text)
    }

    override fun showAdded(text: String) {
        showSnack(view, text)
    }

    override fun showState(teams: List<TeamsItem>) {
        if (!teams.isEmpty()) isFavorite = true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if(!isFavorite){
                    teams.let { presenter.addFav(teams) }
                    isFavorite = !isFavorite
                }else{
                    teams.idTeam?.let { presenter.removeFav(it) }
                    isFavorite = !isFavorite
                }
                setFavorite()
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
    class DetailAdapter(fm: FragmentManager?, val teams: TeamsItem?) : FragmentStatePagerAdapter(fm){
        override fun getItem(position: Int): Fragment {
            val fragment : Fragment
            return when(position)
            {
                0 -> {
                    fragment = OverviewFragment()
                    val bundle = Bundle()
                    bundle.putParcelable("teams", teams)
                    fragment.arguments = bundle
                    fragment
                }
                else -> {
                    fragment = PlayerFragment()
                    val bundle = Bundle()
                    bundle.putParcelable("teams", teams)
                    fragment.arguments = bundle
                    fragment
                }
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when(position){
                0 -> "Overview"
                else -> {
                    "Team Player"
                }
            }
        }

    }

}
