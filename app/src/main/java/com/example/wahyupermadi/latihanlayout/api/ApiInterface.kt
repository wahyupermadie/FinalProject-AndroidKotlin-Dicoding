package com.example.wahyupermadi.latihanlayout.api

import com.example.wahyupermadi.latihanlayout.model.MatchResponse
import com.example.wahyupermadi.latihanlayout.model.TeamResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface{
    @GET("eventsnextleague.php?id=4328")
    fun getNextMatch() : Flowable<MatchResponse>

    @GET("eventspastleague.php?id=4328")
    fun getPastMatch(): Flowable<MatchResponse>

    @GET("lookupteam.php")
    fun getTeamDetail(@Query("id") id: String) : Flowable<TeamResponse>
}