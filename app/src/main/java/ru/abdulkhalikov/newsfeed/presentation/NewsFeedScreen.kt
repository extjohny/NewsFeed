package ru.abdulkhalikov.newsfeed.presentation

import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.abdulkhalikov.newsfeed.domain.NewsFeedState
import ru.abdulkhalikov.newsfeed.domain.NewsItem

@Composable
fun NewsFeedScreen(
    paddingValues: PaddingValues
) {
    val viewModel: NewsFeedViewModel = viewModel()

    val screenState = viewModel.screenState.collectAsState()

    when (val currentState = screenState.value) {
        NewsFeedState.Error -> {
            Log.d("NewsFeedScreen", "Error")
        }

        NewsFeedState.Loading -> {
            ProgressBar()
        }

        is NewsFeedState.Content -> {
            Log.d("NewsFeedScreen", "Content")
            NewsList(
                paddingValues = paddingValues,
                news = currentState.news,
                actionOnNewsItemSwiped = { newsItem ->
                    viewModel.deleteNewsItem(newsItem.id)
                }
            )
        }
    }
}

@Composable
private fun ProgressBar() {
    Box(
        modifier = Modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


@Composable
private fun NewsList(
    paddingValues: PaddingValues,
    news: List<NewsItem>,
    actionOnNewsItemSwiped: (NewsItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        items(news, key = { it.id }) { newsItem ->
            val swipeToDismissBoxState = rememberSwipeToDismissBoxState(confirmValueChange = {
                val isDismissed = setOf(SwipeToDismissBoxValue.EndToStart).contains(it)
                if (isDismissed) {
                    actionOnNewsItemSwiped(newsItem)
                }
                return@rememberSwipeToDismissBoxState isDismissed
            }, positionalThreshold = { it * 0.5f })
            SwipeToDismissBox(
                modifier = Modifier.animateItem(
                    fadeInSpec = null, fadeOutSpec = null, placementSpec = spring<IntOffset>(
                        stiffness = Spring.StiffnessMediumLow,
                        visibilityThreshold = IntOffset.VisibilityThreshold
                    )
                ),
                state = swipeToDismissBoxState,
                backgroundContent = {},
                enableDismissFromStartToEnd = false,
                enableDismissFromEndToStart = true,
            ) {
                NewsItem(
                    modifier = Modifier.padding(8.dp),
                    newsItem = newsItem
                )
            }
        }
    }
}