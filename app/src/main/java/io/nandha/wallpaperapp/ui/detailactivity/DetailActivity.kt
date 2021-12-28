package io.nandha.wallpaperapp.ui.detailactivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import io.nandha.wallpaperapp.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(
            layoutInflater
        )
    }
    private val viewModel: DetailActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val position = intent.extras?.getInt("position") ?: 1
        val adapter = IndividualItemAdapter(viewModel.data)
        binding.viewPager.adapter = adapter
        binding.viewPager.setCurrentItem(position, true)
    }
}