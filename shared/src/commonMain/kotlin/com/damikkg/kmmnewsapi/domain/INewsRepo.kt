package com.damikkg.kmmnewsapi.domain

import com.damikkg.kmmnewsapi.domain.base.CommonFlow
import com.damikkg.kmmnewsapi.domain.models.News
import com.damikkg.kmmnewsapi.domain.models.NewsTypes

interface INewsRepo {
    suspend fun getNewsByCategory(newsType: NewsTypes): List<News>
}