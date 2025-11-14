package ru.abdulkhalikov.newsfeed.data.model

import com.google.gson.annotations.SerializedName

data class NewsItemDto(
    @SerializedName("id") val id: Int,
    @SerializedName("author") val author: String,
    @SerializedName("avatar") val avatarUrl: String,
    @SerializedName("text") val text: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("views") val views: Float,
    @SerializedName("shares") val shares: Float,
    @SerializedName("likes") val likes: Float,
    @SerializedName("publishedAt") val publishedAt: String,
)