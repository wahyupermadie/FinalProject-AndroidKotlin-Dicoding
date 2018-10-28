package com.example.wahyupermadi.latihanlayout.view.teams.detailPlayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.wahyupermadi.latihanlayout.R
import com.example.wahyupermadi.latihanlayout.model.PlayerItem
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity() {
    lateinit var player : PlayerItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        player = intent.getParcelableExtra("player")
        supportActionBar?.title = player.strPlayer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Glide.with(this).load(player.strFanart1).into(iv_detail_player)
        player_detail_deskripsi.text = player.strDescriptionEN
        player_height.text = player.strHeight
        player_weight.text = player.strWeight
        player_detail_role.text = player.strPosition
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
