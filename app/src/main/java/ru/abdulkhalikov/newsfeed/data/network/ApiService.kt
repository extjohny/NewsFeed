package ru.abdulkhalikov.newsfeed.data.network

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import ru.abdulkhalikov.newsfeed.data.model.NewsItemDto
import ru.abdulkhalikov.newsfeed.data.model.UpdateFavouriteStatusRequest
import ru.abdulkhalikov.newsfeed.domain.Comment

interface ApiService {

    @GET("posts")
    suspend fun loadNews(): List<NewsItemDto>

    @GET("posts/{id}")
    suspend fun getNewsItem(
        @Path("id") id: String
    ): NewsItemDto

    @PUT("posts/{id}")
    suspend fun updateLikes(
        @Path("id") newsItemId: String,
        @Body updateLikesRequest: UpdateFavouriteStatusRequest
    ): NewsItemDto

    @DELETE("posts/{id}")
    suspend fun deleteNewsItem(
        @Path("id") id: String
    )
}