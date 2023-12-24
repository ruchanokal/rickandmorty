package com.characters.rickandmorty.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.characters.rickandmorty.R

fun ImageView.downloadImage(url: String, progressDrawable: CircularProgressDrawable) {

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.error)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}



fun placeholderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadUrl")
fun download(view: ImageView, url: String?) {

    url?.let {
        view.downloadImage(it, placeholderProgressBar(view.context))
    }
}
