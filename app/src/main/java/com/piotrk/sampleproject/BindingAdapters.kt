package com.piotrk.sampleproject

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.piotrk.sampleproject.GlideApp
import com.jakewharton.rxbinding.view.RxView
import java.util.concurrent.TimeUnit

@BindingAdapter("onClick")
fun bindOnClick(view: View, listener: Runnable) {
    RxView.clicks(view).throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
        listener.run()
    }
}

@SuppressLint("CheckResult")
@BindingAdapter("imageUrl")
fun setImageUrl(
    imageView: ImageView,
    url: String?
) {
    val placeHolder = R.drawable.placeholder

    if (!url.isNullOrEmpty()) {
        val request = GlideApp.with(imageView.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .centerCrop()

        request.placeholder(placeHolder)
        request.into(imageView)
    } else {
        placeHolder.let {
            //used to fix some of the vector drawables before 6.0
            val drawableCompat = VectorDrawableCompat.create(imageView.resources, it, null)
            imageView.setImageDrawable(drawableCompat)
        }
    }
}