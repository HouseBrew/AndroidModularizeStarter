package com.housebrew.common

object SharedPrefKeys {
}

object Endpoints {

}

object Boardcasts {
}

object TimeFormat {
    const val YYYYMMDDHHmmssSSSZ = "yyyy-MM-dd HH:mm:ss.SSSZ"
    const val YYYYMMDDHHmmssSSSSSSZ = "yyyy-MM-dd HH:mm:ss.SSSSSSZ"
    const val YYYYMMDDHHmmssZZZ = "yyyy-MM-dd HH:mm:ssZZZ"
    const val YYYYMMDDHHmmss = "yyyy-MM-dd HH:mm:ss"
    const val YYYYMMDDHHmm = "yyyyy-MM-dd HH:mm"
    const val YYYYMMDD = "yyyy-MM-dd"
    const val HHmm = "HH:mm"
    const val HHmmss = "HH:mm:ss"
    const val GMT = "EEE, dd MMM yyyy HH:mm:ss z"
    const val MMMDDYYYY = "MMM dd, yyyy"
}

object IntentKey {
}

val NEWS_COUNTRY_OPTIONS = arrayListOf(
    "ae", "ar", "at", "au", "be", "bg", "br", "ca", "ch", "cn", "co", "cu",
    "cz", "de", "eg", "fr", "gb", "gr", "hk", "hu", "id", "ie", "il", "in",
    "it", "jp", "kr", "lt", "lv", "ma", "mx", "my", "ng", "nl", "no", "nz",
    "ph", "pl", "pt", "ro", "rs", "ru", "sa", "se", "sg", "si", "sk", "th",
    "tr", "tw", "ua", "us", "ve", "za"
)

val NEWS_CATEGORY_OPTIONS = arrayListOf(
    "business", "entertainment", "general", "health", "science", "sports", "technology"
)

val PAGE_MAP = mutableMapOf<String, Int>()