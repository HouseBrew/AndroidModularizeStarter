package com.housebrew.common.models

import java.util.Date

enum class RespStatus {
    OK, ERROR
}

data class NewsHeadlineRespModel(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsHeadline>
)

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