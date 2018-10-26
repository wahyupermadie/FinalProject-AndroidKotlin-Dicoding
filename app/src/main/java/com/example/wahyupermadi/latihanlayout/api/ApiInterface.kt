package com.example.wahyupermadi.latihanlayout.api

import com.example.wahyupermadi.latihanlayout.model.LigaResponse
import com.example.wahyupermadi.latihanlayout.model.MatchResponse
import com.example.wahyupermadi.latihanlayout.model.PlayerResponse
import com.example.wahyupermadi.latihanlayout.model.TeamResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface{
    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id: String) : Flowable<MatchResponse>

    @GET("eventspastleague.php")
    fun getPastMatch(@Query("id") id: String): Flowable<MatchResponse>

    @GET("lookupteam.php")
    fun getTeamDetail(@Query("id") id: String) : Flowable<TeamResponse>

    @GET("all_leagues.php")
    fun getLiga() : Flowable<LigaResponse>

    @GET("lookup_all_teams.php")
    fun getTeam(@Query("id") id : String) : Flowable<TeamResponse>

    @GET("lookup_all_players.php")
    fun getPlayer(@Query("id") id : String) : Flowable<PlayerResponse>
}