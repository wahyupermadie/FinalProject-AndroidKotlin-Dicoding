package com.example.wahyupermadi.latihanlayout.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import com.example.wahyupermadi.latihanlayout.model.TeamsItem
import org.jetbrains.anko.db.*

class SqlOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, "db_matchs.db", null, 1) {
    companion object {
        private var instance : SqlOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context) : SqlOpenHelper{
            if(instance == null){
                instance = SqlOpenHelper(context.applicationContext)
            }
            return instance as SqlOpenHelper
        }
    }
    override fun onCreate(database: SQLiteDatabase?) {
        database?.createTable(MatchItem.TABLE_MATCH, true,
            MatchItem.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            MatchItem.ID_MATCH to TEXT + UNIQUE,
            MatchItem.HOME_TEAM to TEXT,
            MatchItem.AWAY_TEAM to TEXT,
            MatchItem.EVENT_DATE to TEXT,
            MatchItem.AWAY_SCORE to TEXT,
            MatchItem.HOME_SCORE to TEXT,
            MatchItem.AWAY_FORMATION to TEXT,
            MatchItem.HOME_FORMATION to TEXT,
            MatchItem.HOME_SHOTS to TEXT,
            MatchItem.AWAY_SHOTS to TEXT,
            MatchItem.HOME_GK to TEXT,
            MatchItem.HOME_DEFF to TEXT,
            MatchItem.HOME_FW to TEXT,
            MatchItem.HOME_MF to TEXT,
            MatchItem.HOME_SUBS to TEXT,
            MatchItem.AWAY_GK to TEXT,
            MatchItem.AWAY_DEFF to TEXT,
            MatchItem.AWAY_FW to TEXT,
            MatchItem.AWAY_MF to TEXT,
            MatchItem.AWAY_SUBS to TEXT,
            MatchItem.ID_HOME to TEXT,
            MatchItem.ID_AWAY to TEXT,
            MatchItem.AWAY_GOAL to TEXT,
            MatchItem.HOME_GOAL to TEXT,
            MatchItem.MATCH_TIME to TEXT)

        database?.createTable(TeamsItem.TABLE_TEAM, true,
            TeamsItem.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TeamsItem.ID_TEAM to TEXT + UNIQUE,
            TeamsItem.TEAM_NAME to TEXT,
            TeamsItem.TEAM_BADGE to TEXT,
            TeamsItem.TEAM_FORMED_YEAR to TEXT,
            TeamsItem.TEAM_STADIUM to TEXT,
            TeamsItem.TEAM_DESCRIPTION to TEXT
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.dropTable(MatchItem.TABLE_MATCH, true)
        p0?.dropTable(TeamsItem.TABLE_TEAM, true)
    }
}
val Context.database : SqlOpenHelper
    get() = SqlOpenHelper.getInstance(applicationContext)