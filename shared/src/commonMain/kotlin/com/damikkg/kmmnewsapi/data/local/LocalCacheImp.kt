package com.damikkg.kmmnewsapi.data.local

import com.damikkg.kmmnewsapi.data.ILocalCache
import com.damikkg.kmmnewsapi.data.local.sqldelight.DatabaseDriverFactory
import com.damikkg.kmmnewsapi.domain.models.News
import com.damikkg.kmmnewsapi.domain.models.NewsTypes
import com.damikkg.kmmnewsapi.local.sqldelight.NewsDB
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.*

class LocalCacheImp (databaseDriverFactory: DatabaseDriverFactory) : ILocalCache {
    private val dao = NewsDB(databaseDriverFactory.createDriver()).newsDBQueries

    override fun getAllNews(): List<News> {
        return try {
            dao.selectAllNews().executeAsList().map {
                News.fromDBEntity(it)
            }
        } catch (e:Exception)
        {
            Napier.e(e.toString())
            emptyList()
        }
    }

    override fun getAllNewsByType(type: NewsTypes): List<News> {
        return try {
            dao.selectNewsByCategoryOrdered(type.path).executeAsList().map {
                News.fromDBEntity(it)
            }
        } catch (e:Exception)
        {
            Napier.e(e.toString())
            emptyList()
        }
    }

    override fun insertNewsEntity(news: News) {
        try {
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
        } catch (e:Exception)
        {
            Napier.e(e.toString())
        }
    }
}