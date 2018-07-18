package com.housebrew.common.apis

import com.housebrew.common.models.NewsHeadlineRespModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit API interface to News API
 */
interface NewsService {
    @GET("v2/top-headlines")
    fun getCountryHeadlines(@Query("country") country: String, @Query("page") pageNumber: Int = 1): Observable<NewsHeadlineRespModel>

    @GET("v2/top-headlines")
    fun getSourceHeadlines(@Query("sources") source: String, @Query("page") pageNumber: Int = 1): Observable<NewsHeadlineRespModel>
}
