package ru.abdulkhalikov.newsfeed.data.model

import com.google.gson.annotations.SerializedName

data class NewsItemDto(
    @SerializedName("id") val id: String,
    @SerializedName("author") val author: String,
    @SerializedName("avatar") val avatarUrl: String,
    @SerializedName("text") val text: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("views") val views: String,
    @SerializedName("shares") val shares: String,
    @SerializedName("likes") val likes: String,
    @SerializedName("publishedAt") val publishedAt: String,
)