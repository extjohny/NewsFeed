package ru.abdulkhalikov.newsfeed.data

import ru.abdulkhalikov.newsfeed.data.model.NewsResponse
import ru.abdulkhalikov.newsfeed.domain.NewsItem

class NewsFeedMapper {

    fun mapResponseToNews(response: NewsResponse): List<NewsItem> {
        val result = mutableListOf<NewsItem>()

        for (newsItemDto in response.news) {
            result.add(
                NewsItem(
                    author = newsItemDto.author,
                    text = newsItemDto.text,
                    date = newsItemDto.date,
                    imageUrl = newsItemDto.imageUrl
                )
            )
        }

        return result
    }
}