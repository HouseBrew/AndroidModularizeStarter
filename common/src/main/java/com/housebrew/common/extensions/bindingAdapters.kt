package com.housebrew.common.extensions

import android.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView

@BindingAdapter("app:imageUrl")
fun SimpleDraweeView.loadImageUrl(url: String) {
    this.setImageURI(url)
}