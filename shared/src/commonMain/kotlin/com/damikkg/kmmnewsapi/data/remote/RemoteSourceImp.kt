package com.damikkg.kmmnewsapi.data.remote

import com.damikkg.kmmnewsapi.data.IRemoteSource
import com.damikkg.kmmnewsapi.data.remote.responses.NewsResponse
import com.damikkg.kmmnewsapi.data.remote.responses.toNews
import com.damikkg.kmmnewsapi.domain.models.News
import com.damikkg.kmmnewsapi.domain.models.NewsTypes
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.request.*

const val BASE_URL = "https://inshortsapi.vercel.app/"

class RemoteSourceImp (private val client: HttpClient) : IRemoteSource
{
    override suspend fun getNews(newsType: NewsTypes): List<News> {
        return try {
            val response:NewsResponse = client.get("$BASE_URL/news?category=${newsType.path}")
            if(response.success)
            {
                response.data.map {
                    it.toNews(newsType.path)
                }
            }
            else
            {
                emptyList()
            }
        } catch (e:Exception)
        {
            Napier.e(e.toString())
            emptyList()
        }
    }
}