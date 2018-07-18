package com.housebrew.modularizestarter.ui.headlines

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.LayoutInflater
import android.view.ViewGroup
import com.housebrew.common.bases.BaseFragment
import com.housebrew.common.bases.BaseViewModel
import com.housebrew.common.di.viewModelBinder
import com.housebrew.common.models.NewsHeadline
import com.housebrew.common.repos.NewsRepo
import com.housebrew.modularizestarter.R
import kotlinx.android.synthetic.main.fragment_headlines.*
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import timber.log.Timber

/**
 * ViewModel for HeadlinesFragment
 * Managing headlines related data and lifecycle
 */
class HeadlinesViewModel(private val newsRepo: NewsRepo) : BaseViewModel() {
    val news: MutableLiveData<ArrayList<NewsHeadline>> = MutableLiveData()

    fun getHeadlines(isRefresh: Boolean = false) {
        compositeDisposable.add(newsRepo.getCountryHeadLines(isRefresh).subscribe {
            Timber.d(it.toString())
            val currentNews = ArrayList(news.value ?: arrayListOf())
            currentNews.addAll(it)
            news.value = currentNews
        })
    }

    fun onRefresh(){
        getHeadlines(true)
    }
}

/**
 * A fragment that shows news headline
 */
class HeadlinesFragment : BaseFragment() {

    private val viewModel by viewModelBinder<HeadlinesViewModel> {
        bind() from provider { HeadlinesViewModel(instance()) }
    }
    private val controller by lazy { NewsBannerListController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = inflater.inflate(R.layout.fragment_headlines, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // When pull to refresh, trigger refresh in viewModel
        refreshLayout.setOnRefreshListener { viewModel.onRefresh() }

        // When the news in view model is changed, re-render the recyclerview items
        viewModel.news.observe(this, Observer {
            refreshLayout.isRefreshing = false
            controller.setData(it)
        })

        // To make recyclerview stop on 1 item after scrolling
        LinearSnapHelper().attachToRecyclerView(recyclerHeadlines)
        // Set Horizontal LinearLayoutManager
        recyclerHeadlines.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerHeadlines.setController(controller)
        viewModel.getHeadlines()
    }
}