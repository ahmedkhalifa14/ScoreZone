package com.example.scorezone.helper

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
fun extractDateTimeLocal(dateTimeString: String): Pair<String, String> {
    val dateTime = ZonedDateTime.parse(dateTimeString).withZoneSameInstant(ZoneId.systemDefault())
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val date = dateTime.format(dateFormatter)
    val time = dateTime.format(timeFormatter)
    return Pair(date, time)
}