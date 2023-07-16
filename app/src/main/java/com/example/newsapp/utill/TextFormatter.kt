package com.example.newsapp.utill

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object TextFormatter {

    @Throws(ParseException::class)
    fun customDateFormatter(
        inputDate: String?,
        INPUT_DATE_FORMAT: String?,
        OUTPUT_DATE_FORMAT: String?
    ): String? {
        val cal = Calendar.getInstance()
        val sdf = SimpleDateFormat(INPUT_DATE_FORMAT, Locale.ENGLISH)
        try {
            if (inputDate != null) {
                cal.time = sdf.parse(inputDate)
                val date = cal.time
                return SimpleDateFormat(OUTPUT_DATE_FORMAT, Locale.ENGLISH).format(date)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}