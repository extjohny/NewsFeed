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
    val publishedAt: String,
    val statistics: List<StatisticItem>
) : Parcelable