package com.example.myapplication.viewbinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object ViewBinding {
    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(view: ImageView, url: String) {
        view.context.let {
            Glide.with(it).load(url).diskCacheStrategy(DiskCacheStrategy.DATA).into(view)
        }
    }
}