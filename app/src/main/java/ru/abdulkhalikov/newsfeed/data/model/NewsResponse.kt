package ru.abdulkhalikov.newsfeed.data.model

import kotlinx.serialization.SerialName

data class NewsResponse(
    @SerialName("articles") val news: List<NewsItemDto>
)