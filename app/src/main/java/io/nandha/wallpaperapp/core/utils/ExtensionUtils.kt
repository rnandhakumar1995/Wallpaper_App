package io.nandha.wallpaperapp.core.utils

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.nandha.wallpaperapp.R
import io.nandha.wallpaperapp.data.model.Image
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

fun String.toImageList(): List<Image> {
    val listType: Type = object : TypeToken<List<Image>>() {}.type
    return Gson().fromJson(
        this, listType
    )
}

fun List<Image>.sortByDate(): List<Image> {
    return sortedByDescending {
        val date = SimpleDateFormat("yyyy-MM-dd").parse(it.date)
        return@sortedByDescending date.time
    }
}

fun RequestBuilder<Drawable>.showLoading(context: Context): RequestBuilder<Drawable> {
    return placeholder(R.drawable.loading).thumbnail(Glide.with(context).load(R.drawable.loading))
}