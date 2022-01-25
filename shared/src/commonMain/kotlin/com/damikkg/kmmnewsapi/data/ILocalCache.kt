package com.damikkg.kmmnewsapi.data

import com.damikkg.kmmnewsapi.domain.models.News
import com.damikkg.kmmnewsapi.domain.models.NewsTypes
import kotlinx.coroutines.flow.Flow

interface ILocalCache {
    fun getAllNews () : List<News>
    fun getAllNewsByType (type:NewsTypes) : List<News>
    fun insertNewsEntity (news:News)
}