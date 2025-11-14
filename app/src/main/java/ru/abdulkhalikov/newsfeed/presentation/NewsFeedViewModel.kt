package ru.abdulkhalikov.newsfeed.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.abdulkhalikov.newsfeed.data.NewsFeedMapper
import ru.abdulkhalikov.newsfeed.data.network.ApiFactory
import ru.abdulkhalikov.newsfeed.domain.NewsFeedState

class NewsFeedViewModel : ViewModel() {

    private val _screenState = MutableStateFlow<NewsFeedState>(NewsFeedState.Loading)
    val screenState = _screenState.asStateFlow()

    private val mapper = NewsFeedMapper()

    private val apiService = ApiFactory.apiService

    init {
        viewModelScope.launch {
            val news = mapper.mapResponseToNews(apiService.loadNews())
            _screenState.value = NewsFeedState.Content(news)
        }
    }
}