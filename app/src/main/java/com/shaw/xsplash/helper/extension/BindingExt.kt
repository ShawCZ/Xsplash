package com.shaw.xsplash.helper.extension

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.shaw.xsplash.helper.GlideApp
import timber.log.Timber

/**
 * Created on 2019/6/5.
 * @author XCZ
 */

@BindingAdapter(value = ["imageUrl","imageHeight"], requireAll = false)
fun AppCompatImageView.setImageUrl(imageUrl: Int?,imageHeight: Int?) {
    Timber.d("setImageUrl imageHeight = $layoutParams")
    layoutParams.height = imageHeight!!
    layoutParams = layoutParams
    GlideApp.with(this.context)
        .load(imageUrl)
        .into(this)
}