package com.housebrew.common.repos

import com.housebrew.common.FetchNewsError
import com.housebrew.common.NEWS_CATEGORY_OPTIONS
import com.housebrew.common.PAGE_MAP
import com.housebrew.common.apis.NewsService
import com.housebrew.common.extensions.SchedulerProvider
import com.housebrew.common.extensions.ignoreCaseEqual
import com.housebrew.common.models.NewsHeadline
import com.housebrew.common.models.NewsHeadlineRespModel
import com.housebrew.common.models.RespStatus
import io.reactivex.Observable
import java.util.Locale

/**
 * A repo for handling operations related to News
 *
 * TODO include Room Database as source to enhance user experience
 *
 * @param newsService API interface of News API
 * @param schedulerProvider Provider for Rx Schedulers
 */
class NewsRepo(private val newsService: NewsService, private val schedulerProvider: SchedulerProvider) {

    /**
     * Get headlines of a country from News API. By default is the current locale. If the default is not
     * supported by News API, fallback to fetch US news
     *
     * @param isRefresh indicate fetch from page 1 or next page
     * @return An observable of a list of fetched news
     * @throws FetchNewsError when the API returns error, throw exception and let the UI to handle
     */
    fun getCountryHeadLines(isRefresh: Boolean = false): Observable<List<NewsHeadline>> {
        val localeCountry = Locale.getDefault().country.toLowerCase()
        val country = if (localeCountry in NEWS_CATEGORY_OPTIONS) localeCountry else "us"
        val page = if (isRefresh) 1 else PAGE_MAP[country] ?: 1
        return newsService.getCountryHeadlines(
            country = country,
            pageNumber = page)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnNext {
                if (it.status.ignoreCaseEqual(RespStatus.OK.name)) {
                    PAGE_MAP[country] = page + 1
                }
            }
            .map {
                if (it.status.ignoreCaseEqual(RespStatus.OK.name))
                    return@map it.articles

                throw FetchNewsError()
            }
    }

    /**
     * Get headlines news source from News API.
     *
     * @param source news source
     * @return An observable of a list of fetched news
     * @throws FetchNewsError when the API returns error, throw exception and let the UI to handle
     */
    fun getSourceHeadLines(source: String): Observable<List<NewsHeadline>> {
        val page = PAGE_MAP[source] ?: 1
        return newsService.getSourceHeadlines(
            source = source,
            pageNumber = page)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnNext {
                if (it.status.ignoreCaseEqual(RespStatus.OK.name)) {
                    PAGE_MAP[source] = page + 1
                }
            }
            .map {
                if (it.status.ignoreCaseEqual(RespStatus.OK.name))
                    return@map it.articles

                throw FetchNewsError()
            }
    }
}