package com.damikkg.kmmnewsapi.data.local

import com.damikkg.kmmnewsapi.data.ILocalCache
import com.damikkg.kmmnewsapi.data.local.sqldelight.DatabaseDriverFactory
import com.damikkg.kmmnewsapi.domain.models.News
import com.damikkg.kmmnewsapi.domain.models.NewsTypes
import com.damikkg.kmmnewsapi.local.sqldelight.NewsDB
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.*

class LocalCacheImp (private val databaseDriverFactory: DatabaseDriverFactory) : ILocalCache {
    private val dao = NewsDB(databaseDriverFactory.createDriver()).newsDBQueries

    override fun getAllNews(): List<News> {
        return dao.selectAllNews().executeAsList().map {
            News.fromDBEntity(it)
        }
    }

    override fun getAllNewsByType(type: NewsTypes): List<News> {
        return dao.selectNewsByCategoryOrdered(type.path).executeAsList().map {
            News.fromDBEntity(it)
        }
    }

    override fun insertNewsEntity(news: News) {
        dao.insertNews(
            author = news.author,
            content = news.content,
            imageUrl = news.imageUrl,
            readMoreUrl = news.readMoreUrl,
            title =  news.title,
            url = news.url,
            timestamp = news.timestamp,
            category = news.category
        )
    }
}