package com.example.dailyinform.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions


@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
//            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)

//        val imageLoader = ImageLoaderBuilder.getBuilder().build()
//        imageLoader.with(view.context).load(imageUrl,view)
    }
}
