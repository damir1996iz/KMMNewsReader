package com.damikkg.kmmnewsapi.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import com.damikkg.kmmnewsapi.android.presentation.MainScreen
import com.damikkg.kmmnewsapi.feature.NewsViewModel
import org.koin.android.ext.android.inject

@ExperimentalMaterialApi
class MainActivity : AppCompatActivity(){

    private val newsViewModel:NewsViewModel by inject ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(newsViewModel = newsViewModel)
        }
    }
}