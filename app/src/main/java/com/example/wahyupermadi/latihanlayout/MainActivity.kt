package com.example.wahyupermadi.latihanlayout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.wahyupermadi.latihanlayout.R.id.*
import com.example.wahyupermadi.latihanlayout.view.favorite.FavoriteFragment
import com.example.wahyupermadi.latihanlayout.view.matchs.next.NextMatchFragment
import com.example.wahyupermadi.latihanlayout.view.matchs.past.PastMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                last_match -> {
                    loadLastFragment(savedInstanceState)
                }
                next_match -> {
                    loadNextFragment(savedInstanceState)
                }
                favorites -> {
                    loadFavoriteFragment(savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = last_match
    }

    private fun loadLastFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.viewpager_main, PastMatchFragment(), PastMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadNextFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.viewpager_main, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
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
}
