package com.example.wahyupermadi.latihanlayout.view.teams.overview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.model.TeamsItem
import kotlinx.android.synthetic.main.fragment_overview.*

class OverviewFragment : Fragment(){
    var team : TeamsItem? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val bundle = arguments
        team = bundle?.getParcelable("teams")

        tv_overview.text = team?.strDescriptionEN
    }
}