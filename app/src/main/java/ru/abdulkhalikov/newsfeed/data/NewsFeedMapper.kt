package ru.abdulkhalikov.newsfeed.data

import ru.abdulkhalikov.newsfeed.data.model.NewsItemDto
import ru.abdulkhalikov.newsfeed.domain.NewsItem
import ru.abdulkhalikov.newsfeed.domain.StatisticItem
import ru.abdulkhalikov.newsfeed.domain.StatisticType

class NewsFeedMapper {

    fun mapResponseToNews(response: List<NewsItemDto>): List<NewsItem> {
        val result = mutableListOf<NewsItem>()

        for (newsItemDto in response) {
            result.add(
                NewsItem(
                    id = newsItemDto.id.toInt(),
                    author = newsItemDto.author,
                    avatarUrl = newsItemDto.avatarUrl,
                    text = newsItemDto.text,
                    publishedAt = newsItemDto.publishedAt,
                    imageUrl = newsItemDto.imageUrl,
                    statistics = listOf(
                        StatisticItem(
                            type = StatisticType.VIEWS,
                            count = transformStatisticToInt(newsItemDto.views)
                        ),
                        StatisticItem(
                            type = StatisticType.LIKES,
                            count = transformStatisticToInt(newsItemDto.likes)
                        ),
                        StatisticItem(
                            type = StatisticType.SHARES,
                            count = transformStatisticToInt(newsItemDto.shares)
                        )
                    )
                )
            )
        }
        return result
    }

    private fun transformStatisticToInt(count: String): Int {
        return count.toFloat().toInt()
    }
}