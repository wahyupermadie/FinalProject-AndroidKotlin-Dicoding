package com.example.wahyupermadi.latihanlayout.view.matchs

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.view.matchs.next.NextMatchFragment
import com.example.wahyupermadi.latihanlayout.view.matchs.past.PastMatchFragment
import com.example.wahyupermadi.latihanlayout.view.matchs.search.SearchMatchActivity
import kotlinx.android.synthetic.main.match_fragment.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity


class MatchFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.match_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fragmentAdapter = MyPagerAdapter(fragmentManager, ctx)
        viewpager_match.adapter = fragmentAdapter
        tabs_match.setupWithViewPager(viewpager_match)
        (activity as AppCompatActivity).supportActionBar?.title = context?.getString(R.string.past_event_title)
        tabs_match.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position.let { viewpager_match.currentItem = it ?: 0 }
                if(tab?.position == 1){
                    (activity as AppCompatActivity).supportActionBar?.title = context?.getString(R.string.next_event_title)
                }else{
                    (activity as AppCompatActivity).supportActionBar?.title = context?.getString(R.string.past_event_title)
                }
            }

        })
    }
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.search_icon, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.id_search->
                startActivity<SearchMatchActivity>()
        }
        return true
    }

    class MyPagerAdapter(fm: FragmentManager?, val context: Context) : FragmentStatePagerAdapter(fm) {
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
                0 -> context.getString(R.string.past_event_title)

                else -> {
                    context.getString(R.string.next_event_title)
                }
            }
        }

    }
}