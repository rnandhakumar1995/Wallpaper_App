package io.nandha.wallpaperapp.core.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.nandha.wallpaperapp.data.model.Image
import java.lang.reflect.Type

fun String.toImageList(): List<Image> {
    val listType: Type = object : TypeToken<List<Image>>() {}.type
    return Gson().fromJson(
        this, listType
    )
}