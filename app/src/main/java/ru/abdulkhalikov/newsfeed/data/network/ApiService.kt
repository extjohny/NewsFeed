package ru.abdulkhalikov.newsfeed.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.abdulkhalikov.newsfeed.data.model.NewsResponse

interface ApiService {

    @GET("v2/everything")
    suspend fun loadNews(
        @Query("q") query: String = "android",
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20,
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = "17d1f4d1665f4a418bde43af69d7073a"
    ): NewsResponse
}