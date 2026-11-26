package com.example.googlebooks.network

import com.example.googlebooks.BuildConfig
import com.example.googlebooks.data.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleBooksApi {

    @GET("volumes")
    suspend fun searchVolumes(
        @Query("q") query: String,
        @Query("maxResults") maxResults: Int = 20,
        @Query("key") apiKey: String = BuildConfig.GOOGLE_BOOKS_API_KEY
    ): BooksResponse
}
