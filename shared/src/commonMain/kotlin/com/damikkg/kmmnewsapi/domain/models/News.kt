package com.damikkg.kmmnewsapi.domain.models

/**
 * This class is used to represent one news entity
 */
data class News(
    val author:String,
    val content:String,
    val imageUrl:String,
    val readMoreUrl:String,
    val title:String,
    val url:String,
    val timestamp:Long,
    val category:String
)
{
    companion object
    {
        fun fromDBEntity(n:com.damikkg.kmmnewsapi.data.local.sqldelight.News) : News
        {
            return News(
                n.author ?: "",
                n.content ?:"",
                n.imageUrl ?:"",
                n.readMoreUrl ?: "",
                n.title ?: "",
                n.url ?: "",
                n.timestamp ?: 0,
                n.category ?: "all"
            )
        }
    }
}