package com.example.shiftlab_task.common

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.icu.util.TimeZone
import java.util.Date
import java.util.Locale

object DateFormatter {
    fun formatDate(date: Date?): String {
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return if (date != null) {
            val utilDate = if (date > Calendar.getInstance().time) {
                Calendar.getInstance().time
            } else {
                date
            }
            formatter.format(utilDate)
        } else {
            Constants.EMPTY_STRING
        }
    }

    fun formatDateToISO8601(date: Date?): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        return if (date != null) {
            val utilDate = if (date > Calendar.getInstance().time) {
                Calendar.getInstance().time
            } else {
                date
            }
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            formatter.format(utilDate)
        } else {
            Constants.EMPTY_STRING
        }
    }
}