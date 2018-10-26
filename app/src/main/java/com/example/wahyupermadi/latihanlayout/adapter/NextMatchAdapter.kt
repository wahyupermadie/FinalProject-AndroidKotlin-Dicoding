package com.example.wahyupermadi.latihanlayout.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import com.example.wahyupermadi.latihanlayout.utils.showIndonesianDateTime
import com.example.wahyupermadi.latihanlayout.utils.toGMTFormat
import kotlinx.android.synthetic.main.next_list.view.*

class NextMatchAdapter(private val matchs : List<MatchItem>, private val listener : (MatchItem) -> Unit) : RecyclerView.Adapter<MatchViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.next_list, parent, false))
    }

    override fun getItemCount(): Int {
        return matchs.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matchs[position], listener)
    }

}

class MatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(matchItem: MatchItem, listener: (MatchItem) -> Unit) {
        var localDate = ""
        var localTime = ""
        if(matchItem.dateEvent != null || matchItem.strTime != null){
            val dateEvent = toGMTFormat(matchItem.dateEvent, matchItem.strTime)
            val patternDate = "EEEE, dd MMM yyyy";
            localDate = showIndonesianDateTime(
                dateEvent, patternDate)
            val patternTime = "HH:mm";
            localTime = showIndonesianDateTime(
                dateEvent, patternTime
            )
        }
        itemView.tv_away_next_name.text = matchItem.strAwayTeam
        itemView.tv_away_next_score.text = matchItem.intAwayScore
        itemView.tv_home_next_name.text = matchItem.strHomeTeam
        itemView.tv_home_next_score.text = matchItem.intHomeScore
        itemView.tv_nextm_date.text = localDate
        itemView.tv_next_time.text = localTime
        itemView.setOnClickListener {
            listener(matchItem)
        }
    }
}
