package com.damikkg.kmmnewsapi.data.converters

import io.github.aakira.napier.Napier
import platform.CoreFoundation.kCFAbsoluteTimeIntervalSince1970
import platform.Foundation.*

actual fun dateTimeStringsToLong(date:String, time:String):Long
{
    val tmpDateStr = date.split(",")[0]
    val formattedStr = "$tmpDateStr $time"

    val formatter = NSDateFormatter()
    formatter.locale = NSLocale("us")
    formatter.dateFormat = "dd MMM yyyy hh:mm a"
    val nsDate:NSDate = formatter.dateFromString(formattedStr)

    return nsDate?.timeIntervalSince1970?.toLong() ?: 0
}