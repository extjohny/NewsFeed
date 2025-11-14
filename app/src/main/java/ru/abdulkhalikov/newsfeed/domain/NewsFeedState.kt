package ru.abdulkhalikov.newsfeed.domain

sealed class NewsFeedState {

    data object Loading : NewsFeedState()

    data object Error : NewsFeedState()

    data class Content(val news: List<NewsItem>) : NewsFeedState()
}