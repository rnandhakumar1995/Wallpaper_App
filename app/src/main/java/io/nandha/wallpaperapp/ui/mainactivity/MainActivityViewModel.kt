package io.nandha.wallpaperapp.ui.mainactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import io.nandha.wallpaperapp.data.model.Image

class MainActivityViewModel(app: Application) : AndroidViewModel(app) {
    private var listType: Type = object : TypeToken<List<Image>>() {}.type
    private val rawData = app.assets.open("data.json").bufferedReader().use { it.readText() }
    val data: List<Image> = Gson().fromJson(
        rawData, listType
    )
}