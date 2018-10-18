package com.example.wahyupermadi.latihanlayout.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import kotlinx.android.synthetic.main.next_list.view.*

class FavoriteAdapter(private val matchs : List<MatchItem>, private val listener : (MatchItem) -> Unit)
    : RecyclerView.Adapter<FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.next_list, parent, false))
    }

    override fun getItemCount(): Int {
        return matchs.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(matchs[position], listener)
    }
}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(matchItem: MatchItem, listener: (MatchItem) -> Unit){
        itemView.tv_away_next_name.text = matchItem.strAwayTeam
        itemView.tv_away_next_score.text = matchItem.intAwayScore
        itemView.tv_home_next_name.text = matchItem.strHomeTeam
        itemView.tv_home_next_score.text = matchItem.intHomeScore
        itemView.tv_nextm_date.text = matchItem.dateEvent
        itemView.setOnClickListener {
            listener(matchItem)
        }
    }
}