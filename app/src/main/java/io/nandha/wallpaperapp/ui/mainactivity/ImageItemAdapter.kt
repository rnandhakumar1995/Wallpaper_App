package io.nandha.wallpaperapp.ui.mainactivity

import android.content.Intent
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
import io.nandha.wallpaperapp.ui.detailactivity.DetailActivity

class ImageItemAdapter(private val images: List<Image>) :
    RecyclerView.Adapter<ImageItemAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val title: TextView = view.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = images[holder.adapterPosition]
        Glide
            .with(holder.itemView.context)
            .load(image.url).showLoading(holder.itemView.context)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.image)
        holder.title.text = image.title
        holder.itemView.setOnClickListener {
            it.context?.also { context ->
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("position", holder.adapterPosition)
                context.startActivity(intent)
            }
        }

    }

    override fun getItemCount() = images.size
}