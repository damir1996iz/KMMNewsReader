package com.damikkg.kmmnewsapi.feature

import com.damikkg.kmmnewsapi.domain.INewsRepo
import com.damikkg.kmmnewsapi.domain.models.News
import com.damikkg.kmmnewsapi.domain.models.NewsTypes
import com.damikkg.kmmnewsapi.feature.base.SharedViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsViewModel (private val repo:INewsRepo) : SharedViewModel(){
    val currentNewsType = MutableStateFlow(NewsTypes.Any)
    val news = MutableStateFlow<List<News>>(emptyList())

    fun loadNews(type:NewsTypes)
    {
        sharedScope.launch {
            news.update {
                repo.getNewsByCategory(type)
            }
        }
        currentNewsType.update {
            type
        }
    }
}