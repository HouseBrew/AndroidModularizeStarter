package com.housebrew.common.models

import java.util.Date

/**
 * Variation of News API response status
 */
enum class RespStatus {
    OK, ERROR
}

/**
 * Model for serializing the News API headline response
 */
data class NewsHeadlineRespModel(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsHeadline>
)

/**
 * Model for serializing the News headline articles
 */
data class NewsHeadline(
    val source: NewsSource = NewsSource(),
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: Date
) {
    class NewsSource(val id: String = "", val name: String = "")
}