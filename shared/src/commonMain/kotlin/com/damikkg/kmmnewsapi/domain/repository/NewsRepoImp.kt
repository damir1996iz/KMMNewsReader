package com.damikkg.kmmnewsapi.domain.repository

import com.damikkg.kmmnewsapi.data.ILocalCache
import com.damikkg.kmmnewsapi.data.IRemoteSource
import com.damikkg.kmmnewsapi.domain.INewsRepo
import com.damikkg.kmmnewsapi.domain.models.News
import com.damikkg.kmmnewsapi.domain.models.NewsTypes
import io.github.aakira.napier.Napier

class NewsRepoImp (
            private val remoteSource : IRemoteSource,
            private val localCache: ILocalCache
        ): INewsRepo
{
    override suspend fun getNewsByCategory(newsType: NewsTypes): List<News> {
        if (newsType == NewsTypes.All)
        {
            return localCache.getAllNews()
        }
        remoteSource.getNews(newsType).forEach {
            localCache.insertNewsEntity(it)
        }
        return localCache.getAllNewsByType(newsType)
    }
}