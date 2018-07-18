package com.housebrew.common.apis

import android.content.Context
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.housebrew.common.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


fun getRetrofit(context: Context): Retrofit = Retrofit.Builder()
    .baseUrl("https://newsapi.org/")
    .client(OkHttpClient.Builder()
        .addInterceptor {
            val req = it.request().newBuilder()
                .addHeader("X-Api-Key", context.getString(R.string.news_api_key))
                .build()
            it.proceed(req)
        }
        .addNetworkInterceptor(StethoInterceptor())
        .build())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create(
        GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .serializeNulls()
            .create()
    ))
    .build()
