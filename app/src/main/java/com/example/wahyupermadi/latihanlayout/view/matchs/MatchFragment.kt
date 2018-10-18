package com.example.wahyupermadi.latihanlayout.view.matchs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.view.matchs.next.NextMatchFragment
import com.example.wahyupermadi.latihanlayout.view.matchs.past.PastMatchFragment
import kotlinx.android.synthetic.main.match_fragment.*

class MatchFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.match_fragment, container, false);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fragmentAdapter = MyPagerAdapter(fragmentManager)
        viewpager_match.adapter = fragmentAdapter
        tabs_match.setupWithViewPager(viewpager_match)
    }

    class MyPagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    PastMatchFragment()
                }
                else -> {
                    NextMatchFragment()
                }
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "Past Event"
                else -> {
                    return "Next Event"
                }
            }
        }
    }

    companion object {
        fun newInstance() : MatchFragment = MatchFragment()
    }
}