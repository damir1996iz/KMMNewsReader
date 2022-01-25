package com.damikkg.kmmnewsapi.data

import com.damikkg.kmmnewsapi.domain.models.News
import com.damikkg.kmmnewsapi.domain.models.NewsTypes

interface IRemoteSource {
    suspend fun getNews(newsType:NewsTypes) : List<News>
}