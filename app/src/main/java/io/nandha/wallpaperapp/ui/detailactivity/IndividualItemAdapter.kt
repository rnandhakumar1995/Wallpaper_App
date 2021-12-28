package io.nandha.wallpaperapp.ui.detailactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import io.nandha.wallpaperapp.R
import io.nandha.wallpaperapp.core.utils.showLoading
import io.nandha.wallpaperapp.data.model.Image

class IndividualItemAdapter(private val images: List<Image>) :
    RecyclerView.Adapter<IndividualItemAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val title: TextView = view.findViewById(R.id.title)
        val explanation: TextView = view.findViewById(R.id.explanation)
        val date: TextView = view.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.individual_image_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = images[holder.adapterPosition]
        Glide
            .with(holder.itemView.context)
            .load(image.hdurl)
            .showLoading(holder.itemView.context)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.image)
        holder.title.text = image.title
        holder.explanation.text = image.explanation
        holder.date.text = image.date

    }

    override fun getItemCount() = images.size
}