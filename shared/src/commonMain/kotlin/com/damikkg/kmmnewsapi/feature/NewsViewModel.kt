package com.damikkg.kmmnewsapi.feature

import com.damikkg.kmmnewsapi.domain.INewsRepo
import com.damikkg.kmmnewsapi.domain.models.News
import com.damikkg.kmmnewsapi.domain.models.NewsTypes
import com.damikkg.kmmnewsapi.feature.base.SharedViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class NewsLoadingState
{
    data class Success(val result:List<News>) : NewsLoadingState()
    object Loading : NewsLoadingState()
    object Error : NewsLoadingState()
}

class NewsViewModel (private val repo:INewsRepo) : SharedViewModel(){
    val currentNewsType = MutableStateFlow(NewsTypes.Cached)

    private val newsFlow = MutableStateFlow<NewsLoadingState>(NewsLoadingState.Success(emptyList()))

    val news
        get() = newsFlow

    init {
        loadNews(NewsTypes.Cached)
    }

    fun loadNews(type:NewsTypes)
    {
        sharedScope.launch {
            newsFlow.value = NewsLoadingState.Loading

                val result = repo.getNewsByCategory(type)

                if (result.isEmpty())
                {
                    newsFlow.value = NewsLoadingState.Error
                }
                else
                {
                    newsFlow.value = NewsLoadingState.Success(result)
                }
        }
        currentNewsType.value = type
    }
}