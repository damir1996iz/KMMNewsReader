package com.damikkg.kmmnewsapi.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.*
import com.damikkg.kmmnewsapi.android.presentation.MainScreen
import com.damikkg.kmmnewsapi.domain.models.NewsTypes
import com.damikkg.kmmnewsapi.feature.NewsViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val newsViewModel:NewsViewModel by inject ()

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(newsViewModel = newsViewModel)
        }
    }
}