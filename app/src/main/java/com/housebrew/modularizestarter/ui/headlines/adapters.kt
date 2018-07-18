package com.housebrew.modularizestarter.ui.headlines

import android.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.TypedEpoxyController
import com.housebrew.common.models.NewsHeadline
import com.housebrew.modularizestarter.BR
import com.housebrew.modularizestarter.R

@EpoxyModelClass(layout = R.layout.vh_news_banner)
abstract class NewsBannerModel : DataBindingEpoxyModel() {
    @EpoxyAttribute
    lateinit var news: NewsHeadline

    override fun setDataBindingVariables(binding: ViewDataBinding?) {
        binding?.let {
            it.setVariable(BR.news, news)
        }
    }
}

class NewsBannerListController : TypedEpoxyController<ArrayList<NewsHeadline>>() {
    override fun buildModels(data: ArrayList<NewsHeadline>?) {
        data?.forEach {
            NewsBannerModel_()
                .id(it.hashCode())
                .news(it)
                .addTo(this)
        }
    }
}