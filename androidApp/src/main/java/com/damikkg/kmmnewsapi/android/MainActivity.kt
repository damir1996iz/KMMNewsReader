package com.damikkg.kmmnewsapi.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.damikkg.kmmnewsapi.android.presentation.NewsScreen
import com.damikkg.kmmnewsapi.domain.INewsRepo
import com.damikkg.kmmnewsapi.domain.models.NewsTypes
import com.damikkg.kmmnewsapi.feature.NewsViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val newsViewModel:NewsViewModel by inject ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsViewModel.loadNews(NewsTypes.Sciense)

        setContent {
            Scaffold(topBar = {}, floatingActionButton = {
                FloatingActionButton(onClick = {
                    newsViewModel.loadNews(NewsTypes.Business)
                }) {
                    Icon(Icons.Default.Settings,"floatingButtonIcon")
                }
            }) {
                NewsScreen(newsViewModel)
            }
        }
    }
}