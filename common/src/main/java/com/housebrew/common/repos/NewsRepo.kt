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

class NewsRepo(private val newsService: NewsService, private val schedulerProvider: SchedulerProvider) {

    fun getCountryHeadLines(): Observable<List<NewsHeadline>> {
        val localeCountry = Locale.getDefault().country.toLowerCase()
        val country = if (localeCountry in NEWS_CATEGORY_OPTIONS) localeCountry else "us"
        val page = PAGE_MAP[country] ?: 1
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