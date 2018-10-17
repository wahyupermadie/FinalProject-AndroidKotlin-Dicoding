package com.example.wahyupermadi.latihanlayout.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.wahyupermadi.latihanlayout.model.MatchItem
import org.jetbrains.anko.db.*

class SqlOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, "db_match.db", null, 1) {
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
            MatchItem.HOME_SCORE to TEXT)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.dropTable(MatchItem.TABLE_MATCH, true)
    }
}
val Context.database : SqlOpenHelper
    get() = SqlOpenHelper.getInstance(applicationContext)