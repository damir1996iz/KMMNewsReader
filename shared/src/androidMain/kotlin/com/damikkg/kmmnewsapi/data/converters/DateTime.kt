package com.damikkg.kmmnewsapi.data.converters

import java.text.SimpleDateFormat
import java.util.*

actual fun dateTimeStringsToLong(date:String, time:String):Long
{
    return try {
        val tmpDateStr = date.split(",")[0]
        val formattedStr = "$tmpDateStr $time"
        SimpleDateFormat("dd MMM yyyy hh:mm aaa", Locale.US).parse(formattedStr)?.time ?: 0
    } catch (e:Exception)
    {
        0
    }
}