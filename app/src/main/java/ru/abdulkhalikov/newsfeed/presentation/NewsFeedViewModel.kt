package ru.abdulkhalikov.newsfeed.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.abdulkhalikov.newsfeed.data.NewsFeedRepository
import ru.abdulkhalikov.newsfeed.domain.NewsFeedState

class NewsFeedViewModel : ViewModel() {

    private val repository = NewsFeedRepository()

    private val _screenState = MutableStateFlow<NewsFeedState>(NewsFeedState.Loading)
    val screenState = _screenState.asStateFlow()

    init {
        loadNewsFeed()
    }

    private fun loadNewsFeed() = viewModelScope.launch {
        _screenState.value = NewsFeedState.Loading
        repository.loadNews()
        _screenState.value = NewsFeedState.Content(news = repository.news.value)
    }

    fun deleteNewsItem(id: Int) = viewModelScope.launch {
        repository.deleteNewsItem(id.toString())
        _screenState.value = NewsFeedState.Content(news = repository.news.value)
    }
}