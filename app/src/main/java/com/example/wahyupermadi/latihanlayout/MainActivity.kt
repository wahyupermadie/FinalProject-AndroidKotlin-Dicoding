package com.example.wahyupermadi.latihanlayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.wahyupermadi.latihanlayout.R.id.*
import com.example.wahyupermadi.latihanlayout.view.favorite.FavoriteFragment
import com.example.wahyupermadi.latihanlayout.view.matchs.MatchFragment
import com.example.wahyupermadi.latihanlayout.view.teams.TeamFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                matchs -> {
                    loadLastFragment(savedInstanceState)
                }
                teams -> {
                   loadTeamFragment(savedInstanceState)
                }
                favorites -> {
                    loadFavoriteFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = matchs
    }

    private fun loadLastFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.viewpager_main, MatchFragment(), MatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.viewpager_main, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.viewpager_main, TeamFragment(), TeamFragment::class.java.simpleName)
                .commit()
        }
    }
}