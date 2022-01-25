package com.damikkg.kmmnewsapi.android.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.damikkg.kmmnewsapi.domain.models.NewsTypes

@Composable
fun BottomSheet(selectedNews:NewsTypes, onItemClicked:(NewsTypes)->Unit)
{
    Column {
        NewsTypes.values().forEach {
            if(it == selectedNews) {
                NewsCard(text = it.desc, color = Color.LightGray) {
                    onItemClicked(it)
                }
            }
            else
            {
                NewsCard(text = it.desc, color = Color.Gray) {
                    onItemClicked(it)
                }
            }
        }
    }
}

@Composable
fun NewsCard(text:String, color: Color, onClicked:()->Unit)
{
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable {
            onClicked()
        },
        backgroundColor = color
    )
    {
        Column(modifier = Modifier.padding(8.dp), Arrangement.Center, Alignment.Start) {
            Text(text)
        }
    }
}