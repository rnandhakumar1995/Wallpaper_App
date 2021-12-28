package io.nandha.wallpaperapp.ui.detailactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.nandha.wallpaperapp.core.utils.sortByDate
import io.nandha.wallpaperapp.core.utils.toImageList

class DetailActivityViewModel(app: Application) : AndroidViewModel(app) {
    private val rawData = app.assets.open("data.json").bufferedReader().use { it.readText() }
    val data  = rawData.toImageList().sortByDate()
    var page: Int? = null
}