package com.damikkg.kmmnewsapi.data.remote.responses

import com.damikkg.kmmnewsapi.data.converters.dateTimeStringsToLong
import com.damikkg.kmmnewsapi.domain.models.News
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponseEntity(
    val author:String,
    val content:String,
    val date:String,
    val imageUrl:String,
    val readMoreUrl:String?,
    val time:String,
    val title:String,
    val url:String,
)

fun NewsResponseEntity.toNews(category: String):News
{
    val timestamp = dateTimeStringsToLong(this.date, this.time)

    return News(author,
        content,
        imageUrl,
        readMoreUrl ?: "",
        title,
        url,
        timestamp,
        category)
}

@Serializable
data class NewsResponse (
    val category:String,
    val data:List<NewsResponseEntity>,
    val success:Boolean
)