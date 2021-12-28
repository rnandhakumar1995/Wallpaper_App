package io.nandha.wallpaperapp.ui.detailactivity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import io.nandha.wallpaperapp.R
import io.nandha.wallpaperapp.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(
            layoutInflater
        )
    }

    private val viewModel: DetailActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (viewModel.page == null) {
            viewModel.page = intent.extras?.getInt("position") ?: 0
        }
        setupAdapter()
        setupOnScrollListener()
        binding.next.setOnClickListener(this)
        binding.previous.setOnClickListener(this)
        viewModel.page?.let { binding.viewPager.setCurrentItem(it, true) }
    }

    private fun setupOnScrollListener() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                viewModel.page = position
                binding.previous.alpha = if (position == 0) 0.5f else 1f
                binding.next.alpha = if (position == viewModel.data.size - 1) 0.5f else 1f
            }
        })
    }

    private fun setupAdapter() {
        val adapter = IndividualItemAdapter(viewModel.data)
        binding.viewPager.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onClick(view: View?) {
        viewModel.page?.let {
            binding.viewPager.setCurrentItem(getPosition(view, it), true)
        }
    }

    private fun getPosition(view: View?, it: Int) = when (view?.id) {
        R.id.next -> if (it != viewModel.data.size - 1) it + 1 else it
        R.id.previous -> if (it != 0) it - 1 else it
        else -> it
    }
}