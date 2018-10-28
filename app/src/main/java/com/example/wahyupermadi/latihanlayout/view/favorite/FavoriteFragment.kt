package com.example.wahyupermadi.latihanlayout.view.favorite


import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.view.favorite.match.FavMatchFragment
import com.example.wahyupermadi.latihanlayout.view.favorite.team.FavTeamFragment
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.support.v4.ctx


class FavoriteFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Favorite Match"
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val fragmentAdapter = MyPagerAdapter(fragmentManager, ctx)
        viewpager_favorite.adapter = fragmentAdapter
        tabs_favorite.setupWithViewPager(viewpager_favorite)
        (activity as AppCompatActivity).supportActionBar?.title = context?.getString(R.string.past_event_title)
        tabs_favorite.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position.let { viewpager_favorite.currentItem = it ?: 0 }
                if(tab?.position == 1){
                    (activity as AppCompatActivity).supportActionBar?.title = "Team Favorite"
                }else{
                    (activity as AppCompatActivity).supportActionBar?.title = "Match Favorite"
                }
            }

        })
    }

    class MyPagerAdapter(fm: FragmentManager?, val context: Context) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    FavMatchFragment()
                }
                else -> {
                    FavTeamFragment()
                }
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "Match Favorite"

                else -> {
                    "Team Favorite"
                }
            }
        }

    }
}
