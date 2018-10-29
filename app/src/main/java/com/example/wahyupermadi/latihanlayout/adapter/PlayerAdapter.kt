package com.example.wahyupermadi.latihanlayout.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.model.PlayerItem
import kotlinx.android.synthetic.main.player_list.view.*

class PlayerAdapter(private val players : List<PlayerItem>, private val listener : (PlayerItem) -> Unit):RecyclerView.Adapter<PlayerAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PlayerAdapter.ViewHolder {
        return PlayerAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.player_list, parent, false))
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(players[p1], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(
            playerItem: PlayerItem,
            listener: (PlayerItem) -> Unit
        ) {
            itemView.tv_list_player_name.text = playerItem.strPlayer
            Glide.with(itemView.context).load(playerItem.strCutout).into(itemView.iv_list_player)
            itemView.setOnClickListener {
                listener(playerItem)
            }
        }
    }
}