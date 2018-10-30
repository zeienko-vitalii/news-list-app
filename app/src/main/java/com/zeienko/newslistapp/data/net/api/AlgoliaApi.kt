package com.zeienko.newslistapp.data.net.api

import com.zeienko.newslistapp.data.net.models.news.NewsBean
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AlgoliaApi {
    @GET("api/v1/search_by_date?tags=story")
    fun getStories(@Query("page") page: Int?): Single<NewsBean>
}