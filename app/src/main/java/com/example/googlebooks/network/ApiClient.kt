package com.example.googlebooks.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    // Redact the API key query parameter so it never leaks to Logcat.
    private val logging = HttpLoggingInterceptor { message ->
        val sanitized = message.replace(Regex("([?&]key=)[^&\\s]+"), "$1REDACTED")
        HttpLoggingInterceptor.Logger.DEFAULT.log(sanitized)
    }.apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val api: GoogleBooksApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoogleBooksApi::class.java)
    }
}
