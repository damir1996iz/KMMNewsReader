package com.damikkg.kmmnewsapi.android.presentation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.damikkg.kmmnewsapi.feature.NewsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(newsViewModel: NewsViewModel) {
    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    val selectedNewsType = newsViewModel.currentNewsType.collectAsState()

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = {
            BottomSheet(selectedNews = selectedNewsType.value, onItemClicked = {
                newsViewModel.loadNews(it)
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
            })
        })
    {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    coroutineScope.launch {
                        bottomSheetState.show()
                    }
                }) { Icon(Icons.Default.Settings, "floatingButtonIcon") }
            },
        ) {
            NewsScreen(newsViewModel)
        }
    }
}