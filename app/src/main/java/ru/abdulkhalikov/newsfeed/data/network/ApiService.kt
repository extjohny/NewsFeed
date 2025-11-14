package ru.abdulkhalikov.newsfeed.data.network

import retrofit2.http.GET
import ru.abdulkhalikov.newsfeed.data.model.NewsItemDto

interface ApiService {

    @GET("posts")
    suspend fun loadNews(): List<NewsItemDto>
}