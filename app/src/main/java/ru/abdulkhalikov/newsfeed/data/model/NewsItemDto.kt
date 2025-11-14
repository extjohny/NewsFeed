package ru.abdulkhalikov.newsfeed.data.model

import kotlinx.serialization.SerialName

data class NewsItemDto(
    @SerialName("author") val author: String,
    @SerialName("content") val text: String,
    @SerialName("publishedAt") val date: String,
    @SerialName("urlToImage") val imageUrl: String?
) {

    val id: Int
        get() = generateId()

    private fun generateId(): Int = text.hashCode() + imageUrl.hashCode() + date.hashCode()
}