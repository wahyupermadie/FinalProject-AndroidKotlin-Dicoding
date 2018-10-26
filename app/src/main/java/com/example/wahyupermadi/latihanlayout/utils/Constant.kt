package com.example.wahyupermadi.latihanlayout.utils

import android.annotation.SuppressLint
import android.support.constraint.Constraints
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun toGMTFormat(date: String?, time: String?): Date? {
    val formatter = SimpleDateFormat("yyyy-mm-dd HH:mm:ss")
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = "$date $time"
    return formatter.parse(dateTime)
}

@SuppressLint("SimpleDateFormat")
fun showIndonesianDateTime(dateAndTime: Date?,
                           pattern: String) : String {
    val dateStr: String?
    val locale = Locale("in", "ID")
    val formatter = SimpleDateFormat(pattern, locale)

    dateStr = formatter.format(dateAndTime)
    return dateStr
}