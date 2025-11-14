package ru.abdulkhalikov.newsfeed.data.network

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.abdulkhalikov.newsfeed.data.model.NewsItemDto

interface ApiService {

    @GET("posts")
    suspend fun loadNews(): List<NewsItemDto>

    @GET("posts")
    suspend fun loadNewsItem(
        @Query("id") id: Int
    ): NewsItemDto

    @DELETE("posts/{id}")
    suspend fun deleteNewsItem(
        @Path("id") id: String
    )
}