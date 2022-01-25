package com.damikkg.kmmnewsapi.android.presentation

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.rememberImagePainter
import com.damikkg.kmmnewsapi.domain.models.News
import com.damikkg.kmmnewsapi.domain.models.NewsTypes
import com.damikkg.kmmnewsapi.feature.NewsLoadingState
import com.damikkg.kmmnewsapi.feature.NewsViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun NewsScreen(
    newsViewModel: NewsViewModel
)
{
    val news by newsViewModel.news.collectAsState()
    val context = LocalContext.current

    when(news)
    {
        is NewsLoadingState.Success -> {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                (news as NewsLoadingState.Success).result.forEach {
                    NewsCard(news = it)
                    {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.readMoreUrl))
                        startActivity(context, intent, null)
                    }
                }
            }
        }
        is NewsLoadingState.Loading -> {
            Column(modifier = Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                LinearProgressIndicator()
            }
        }
        is NewsLoadingState.Error -> {
            Column(modifier = Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                Text(text = "Loading error. Nothing found in cache")
            }
        }
    }

}

@Composable
fun NewsCard(news:News, onReadMoreClicked:()->Unit)
{
    news.run {
        Card(modifier = Modifier.padding(12.dp)) {
                Column(modifier = Modifier.padding(8.dp), Arrangement.SpaceBetween, Alignment.CenterHorizontally) {

                    Image(painter = rememberImagePainter(imageUrl),
                        contentDescription = "newsImage",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp))

                    Text(title, fontWeight = FontWeight.Bold)
                    Text(content)

                    Row(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Card(backgroundColor = Color.Black) {
                            Text(
                                author,
                                color = Color.White,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.weight(1.0f))
                        Card(
                            modifier = Modifier.clickable { onReadMoreClicked() },
                            backgroundColor = Color.LightGray)
                        {
                            Text(
                                "Read more",
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically)
                    {
                        Text(timestampToString(timestamp), color = Color.LightGray)
                    }
                }
            }
        }
}

fun timestampToString(value:Long) : String
{
    return try {
        SimpleDateFormat("HH:mm dd.MM.yyyy", Locale.US)
            .format(Date(value))
    } catch (e:Exception)
    {
        Log.e("NewsScreen",e.toString())
        "00:00 01.01.1900"
    }
}

@Preview
@Composable
fun NewsCardPreview()
{
//    NewsCard(news = News("damir", "text of news", "", "", "title", "", 10000, "science"))
}