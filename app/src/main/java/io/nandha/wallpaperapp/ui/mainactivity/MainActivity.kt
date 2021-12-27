package io.nandha.wallpaperapp.ui.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import io.nandha.wallpaperapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = activityMainBinding.root
        setContentView(view)
        val imageItemAdapter = ImageItemAdapter(viewModel.data)
        activityMainBinding.imageGrid.layoutManager = GridLayoutManager(this, 3)
        activityMainBinding.imageGrid.adapter = imageItemAdapter
    }
}