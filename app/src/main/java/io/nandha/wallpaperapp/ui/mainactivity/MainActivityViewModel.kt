package io.nandha.wallpaperapp.ui.mainactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class MainActivityViewModel(private val app: Application) : AndroidViewModel(app) {
    val data: String = app.assets.open("data.json").bufferedReader().use { it.readText() }

}