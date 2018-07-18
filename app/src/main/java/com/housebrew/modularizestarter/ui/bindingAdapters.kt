package com.housebrew.modularizestarter.ui

import android.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import com.github.curioustechizen.ago.RelativeTimeTextView
import java.util.Date

@BindingAdapter("app:imageUrl")
fun SimpleDraweeView.loadImageUrl(url: String?) {
    if (url != null)
        this.setImageURI(url)
}

@BindingAdapter("app:reference_time")
fun RelativeTimeTextView.setTime(date: Date) {
    this.setReferenceTime(date.time)
}