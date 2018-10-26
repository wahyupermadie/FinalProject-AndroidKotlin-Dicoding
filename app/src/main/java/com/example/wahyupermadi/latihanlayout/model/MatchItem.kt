package com.example.wahyupermadi.latihanlayout.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchItem(
		val id : Long?,
		var idEvent : String? = null,
		val strHomeTeam : String? = null,
		val strAwayTeam : String? = null,
		val dateEvent : String? = null,
		val intAwayScore : String? = null,
		val intHomeScore  : String? = null,
		var strAwayFormation : String? = null,
		var strHomeFormation : String? = null,
		var intHomeShots : String? = null,
		var intAwayShots : String? = null,
		var strHomeLineupGoalkeeper :  String? = null,
		var strHomeLineupDefense:  String? = null,
		var strHomeLineupForward:  String? = null,
		var strHomeLineupMidfield:  String? = null,
		var strHomeLineupSubstitutes:  String? = null,
		var strAwayLineupGoalkeeper :  String? = null,
		var strAwayLineupDefense:  String? = null,
		var strAwayLineupForward:  String? = null,
		var strAwayLineupMidfield:  String? = null,
		var strAwayLineupSubstitutes:  String? = null,
		var idHomeTeam :  String? = null,
		var idAwayTeam :  String? = null,
		var strAwayGoalDetails :  String? = null,
		var strHomeGoalDetails :  String? = null,
		var strTime: String? = null
):Parcelable{
	companion object {
		const val TABLE_MATCH : String = "TABLE_MATCH"
		const val ID : String = "ID_"
		const val ID_MATCH : String = "ID_MATCH"
		const val AWAY_TEAM : String = "AWAY_TEAM"
		const val HOME_TEAM : String = "HOME_TEAM"
		const val EVENT_DATE : String = "EVENT_DATE"
		const val AWAY_SCORE  : String = "AWAY_SCORE"
		const val HOME_SCORE  : String = "HOME_SCORE"
		const val AWAY_FORMATION : String = "AWAY_FORMATION"
		const val HOME_FORMATION : String = "HOME_FORMATION"
		const val HOME_SHOTS : String = "HOME_SHOTS"
		const val AWAY_SHOTS : String = "AWAY_SHOTS"
		const val HOME_GK : String = "HOME_GK"
		const val HOME_DEFF : String = "HOME_DEFF"
		const val HOME_FW : String = "HOME_FW"
		const val HOME_MF : String = "HOME_MF"
		const val HOME_SUBS : String = "HOME_SUBS"
		const val AWAY_GK : String = "AWAY_GK"
		const val AWAY_DEFF : String = "AWAY_DEFF"
		const val AWAY_FW : String = "AWAY_FW"
		const val AWAY_MF : String = "AWAY_MF"
		const val AWAY_SUBS : String = "AWAY_SUBS"
		const val ID_HOME : String = "ID_HOME"
		const val ID_AWAY : String = "ID_AWAY"
		const val AWAY_GOAL : String = "AWAY_GOAL"
		const val HOME_GOAL : String = "HOME_GOAL"
		const val MATCH_TIME : String = "MATCH_TIME"
	}
}
