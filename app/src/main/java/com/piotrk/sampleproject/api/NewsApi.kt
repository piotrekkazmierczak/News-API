package com.piotrk.sampleproject.api

import com.piotrk.sampleproject.data.model.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/v2//top-headlines/")
    fun getNews(@Query("category") categories: List<String>, @Query("country") country: String = "us", @Query("apiKey") apiKey: String = API_KEY): Single<NewsResponse>
}

const val API_KEY = "ec54961383f646559057d9acf2b46ecc"