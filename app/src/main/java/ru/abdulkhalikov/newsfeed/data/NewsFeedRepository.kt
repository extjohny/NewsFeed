package ru.abdulkhalikov.newsfeed.data

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.HttpException
import ru.abdulkhalikov.newsfeed.data.model.NewsItemDto
import ru.abdulkhalikov.newsfeed.data.model.UpdateFavouriteStatusRequest
import ru.abdulkhalikov.newsfeed.data.network.ApiFactory
import ru.abdulkhalikov.newsfeed.domain.NewsItem

class NewsFeedRepository {

    private val apiService = ApiFactory.apiService

    private val mapper = NewsFeedMapper()

    private lateinit var cachedNews: MutableList<NewsItemDto>

    private val _news = MutableStateFlow<List<NewsItem>>(listOf())
    val news = _news.asStateFlow()

    suspend fun loadNews() {
        val newsDto = apiService.loadNews()
        _news.value = mapper.mapResponseToNews(newsDto)
        cachedNews = newsDto.toMutableList()
    }

    suspend fun deleteNewsItem(id: String) {
        try {
            apiService.deleteNewsItem(id)
            val oldNews = cachedNews
            oldNews.removeIf {
                it.id == id
            }
            cachedNews = oldNews
            _news.value = mapper.mapResponseToNews(oldNews)
        } catch (e: HttpException) {
            e.stackTrace // mock api bug: item were deleted on server, but app crashes with 404
        }
    }

    suspend fun updateFavouriteStatus(newsItemId: Int) {
        val oldNewsItemDto = apiService.getNewsItem(newsItemId.toString())
        if (oldNewsItemDto.isFavourite) {
            val newNewsItemDto = apiService.updateLikes(
                newsItemId = oldNewsItemDto.id,
                updateLikesRequest = UpdateFavouriteStatusRequest(
                    likes = (oldNewsItemDto.likes.toFloat().toInt() - 1).toString(),
                    isFavourite = false
                )
            )
            Log.d("TEST_TEST", "newNewsItemDto isFavorite = true $newNewsItemDto")
            cachedNews = cachedNews.apply {
                replaceAll {
                    if (it.id == newNewsItemDto.id) {
                        newNewsItemDto
                    } else {
                        it
                    }
                }
            }
            Log.d("TEST_TEST", "cachedNews isFavorite = true $cachedNews")
            _news.value = mapper.mapResponseToNews(cachedNews)
        } else {
            val newNewsItemDto = apiService.updateLikes(
                newsItemId = oldNewsItemDto.id,
                updateLikesRequest = UpdateFavouriteStatusRequest(
                    likes = (oldNewsItemDto.likes.toFloat().toInt() + 1).toString(),
                    isFavourite = true
                )
            )
            Log.d("TEST_TEST", "newNewsItemDto isFavorite = false $newNewsItemDto")
            cachedNews = cachedNews.apply {
                replaceAll {
                    if (it.id == newNewsItemDto.id) {
                        newNewsItemDto
                    } else {
                        it
                    }
                }
            }
            Log.d("TEST_TEST", "cachedNews isFavorite = false $cachedNews")
            _news.value = mapper.mapResponseToNews(cachedNews)
        }
    }
}