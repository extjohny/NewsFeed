package ru.abdulkhalikov.newsfeed.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsItem(
    val id: Int,
    val author: String,
    val avatarUrl: String,
    val text: String,
    val imageUrl: String,
    val likes: Int,
    val shares: Int,
    val views: Int,
    val publishedAt: String
) : Parcelable