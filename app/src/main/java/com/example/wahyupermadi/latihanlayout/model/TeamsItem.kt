package com.example.wahyupermadi.latihanlayout.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamsItem(
	val id: Int?,
	@SerializedName("idTeam")
	var idTeam: String? = null,

	@SerializedName("strTeam")
	var strTeam: String? = null,

	@SerializedName("strTeamBadge")
	var strTeamBadge: String? = null,

	@SerializedName("intFormedYear")
	var intFormedYear: String? = null,

	@SerializedName("strStadium")
	var strStadium: String? = null,

	@SerializedName("strDescriptionEN")
	var strDescriptionEN: String? = null

): Parcelable {
	companion object {
		const val TABLE_TEAM: String = "TABLE_TEAM"
		const val ID: String = "ID_"
		const val ID_TEAM: String = "ID_TEAM"
		const val TEAM_NAME: String = "TEAM_NAME"
		const val TEAM_BADGE: String = "TEAM_BADGE"
		const val TEAM_FORMED_YEAR: String = "TEAM_FORMED_YEAR"
		const val TEAM_STADIUM: String = "TEAM_STADIUM"
		const val TEAM_DESCRIPTION: String = "TEAM_DESCRIPTION"
	}
}
