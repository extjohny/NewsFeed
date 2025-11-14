package ru.abdulkhalikov.newsfeed.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.abdulkhalikov.newsfeed.data.model.NewsItemDto
import ru.abdulkhalikov.newsfeed.data.network.ApiFactory
import ru.abdulkhalikov.newsfeed.domain.NewsItem

class NewsFeedRepository {

    private val apiService = ApiFactory.apiService

    private val mapper = NewsFeedMapper()

    private val _news = MutableStateFlow<List<NewsItem>>(listOf())
    val news = _news.asStateFlow()

    suspend fun loadNews() {
        _news.value = mapper.mapResponseToNews(apiService.loadNews())
    }
}