package com.example.wahyupermadi.latihanlayout.view.teams.detailTeam

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import com.example.wahyupermadi.latihanlayout.db.database
import com.example.wahyupermadi.latihanlayout.model.TeamsItem
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailTeamPresenter(private val mView : DetailTeamContract.View , private val context: Context):DetailTeamContract.Presenter{

    override fun getState(id: String) {
        context.database.use {
            val result = select(TeamsItem.TABLE_TEAM)
                .whereArgs("(ID_TEAM = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<TeamsItem>())
            mView.showState(favorite)
        }
    }

    override fun addFav(team: TeamsItem) {
        try {
            context.database.use{
                insert(TeamsItem.TABLE_TEAM,
                    TeamsItem.ID_TEAM to team.idTeam.toString(),
                    TeamsItem.TEAM_NAME to team.strTeam,
                    TeamsItem.TEAM_BADGE to team.strTeamBadge,
                    TeamsItem.TEAM_FORMED_YEAR to team.intFormedYear,
                    TeamsItem.TEAM_STADIUM to team.strStadium,
                    TeamsItem.TEAM_DESCRIPTION to team.strDescriptionEN)
            }
            mView.showAdded("Sukses Menambahkan Favorite")
        }catch (e : SQLiteConstraintException){
            mView.showAdded("Gagal Menambahkan Favorite")
        }
    }

    override fun removeFav(teamId: String) {
        try {
            context.database.use{
                delete(TeamsItem.TABLE_TEAM, "(ID_TEAM = {id})",
                    "id" to teamId)
            }
            mView.showDeleted("Berhasil Menghapus Favorite")
        }catch (e:SQLiteConstraintException){
            mView.showDeleted("Gagal Menghapus Favorite")
        }
    }
}