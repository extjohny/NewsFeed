package ru.abdulkhalikov.newsfeed.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsItem(
    val author: String,
    val text: String,
    val date: String,
    val imageUrl: String?
) : Parcelable {

    val id: Int
        get() = generateId()

    private fun generateId(): Int = text.hashCode() + imageUrl.hashCode() + date.hashCode()
}