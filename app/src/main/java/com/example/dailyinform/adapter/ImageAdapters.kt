package com.example.dailyinform.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.dailyinform.R

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .fallback(R.drawable.aa)
            .error(R.drawable.aa)
//            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)

    }
}
