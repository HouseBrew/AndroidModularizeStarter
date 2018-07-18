package com.housebrew.modularizestarter.ui

import android.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import com.github.curioustechizen.ago.RelativeTimeTextView
import java.util.Date

/**
 * Android DataBinding adapter
 * Extension for binding imageUrl for SimpleDraweeView
 */
@BindingAdapter("app:imageUrl")
fun SimpleDraweeView.loadImageUrl(url: String?) {
    if (url != null)
        this.setImageURI(url)
}

/**
 * Android DataBinding adapter
 * Extension for setting reference time for RelativeTimeTextView
 */
@BindingAdapter("app:reference_time")
fun RelativeTimeTextView.setTime(date: Date) {
    this.setReferenceTime(date.time)
}