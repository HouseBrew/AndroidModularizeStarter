package com.housebrew.common

/**
 * A list of country that supported by News API
 */
val NEWS_COUNTRY_OPTIONS = arrayListOf(
    "ae", "ar", "at", "au", "be", "bg", "br", "ca", "ch", "cn", "co", "cu",
    "cz", "de", "eg", "fr", "gb", "gr", "hk", "hu", "id", "ie", "il", "in",
    "it", "jp", "kr", "lt", "lv", "ma", "mx", "my", "ng", "nl", "no", "nz",
    "ph", "pl", "pt", "ro", "rs", "ru", "sa", "se", "sg", "si", "sk", "th",
    "tr", "tw", "ua", "us", "ve", "za"
)

/**
 * A list of categories that supported by News API
 */
val NEWS_CATEGORY_OPTIONS = arrayListOf(
    "business", "entertainment", "general", "health", "science", "sports", "technology"
)

/**
 * A map that used to track page in runtime
 */
val PAGE_MAP = mutableMapOf<String, Int>()