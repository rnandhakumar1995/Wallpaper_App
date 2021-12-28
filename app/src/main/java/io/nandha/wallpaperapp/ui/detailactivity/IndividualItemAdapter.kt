package io.nandha.wallpaperapp.ui.detailactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomsheet.BottomSheetDialog
import io.nandha.wallpaperapp.R
import io.nandha.wallpaperapp.core.utils.showLoading
import io.nandha.wallpaperapp.data.model.Image

class IndividualItemAdapter(private val images: List<Image>) :
    RecyclerView.Adapter<IndividualItemAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val expand: ImageView = view.findViewById(R.id.expand)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_image_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = images[holder.adapterPosition]
        holder.apply {
            Glide
                .with(itemView.context)
                .load(image.hdurl)
                .showLoading(itemView.context)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(this.image)
            Glide.with(itemView.context).load(R.drawable.arrow).into(this.expand)


            this.expand.setOnClickListener {
                val btmSheet = BottomSheetDialog(itemView.context, R.style.NoBackgroundDialogTheme)
                with(btmSheet) {
                    setContentView(R.layout.image_details)
                    val title: TextView? = findViewById(R.id.title)
                    val explanation: TextView? = findViewById(R.id.explanation)
                    title?.text = image.title
                    explanation?.text = image.explanation
                    show()
                }

            }
        }
    }

    override fun getItemCount() = images.size
}