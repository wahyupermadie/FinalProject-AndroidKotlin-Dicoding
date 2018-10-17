package com.example.wahyupermadi.latihanlayout.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchItem(
		val id : Long?,
		val id_match : String?,
		val strHomeTeam : String?,
		val strAwayTeam : String?,
		val dateEvent : String?,
		val intAwayScore : String?,
		val intHomeScore  : String?,
		var strAwayFormation : String?,
		var strHomeFormation : String?,
		var idEvent : String?,
		var intHomeShots : String?,
		var intAwayShots : String?,
		var strHomeLineupGoalkeeper :  String?,
		var strHomeLineupDefense:  String?,
		var strHomeLineupMidfield:  String?,
		var strHomeLineupForward:  String?,
		var strHomeLineupSubstitutes:  String?,
		var strAwayLineupGoalkeeper :  String?,
		var strAwayLineupDefense:  String?,
		var strAwayLineupMidfield:  String?,
		var strAwayLineupForward:  String?,
		var strAwayLineupSubstitutes:  String?,
		var idHomeTeam :  String?,
		var idAwayTeam :  String?,
		var strAwayGoalDetails :  String?,
		var strHomeGoalDetails :  String?
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
	}
}
