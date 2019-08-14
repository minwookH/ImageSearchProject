package com.minwook.mytriphistory.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.minwook.imagesearch.DAO.Documents
import com.minwook.imagesearch.R
import java.util.ArrayList

class ImageListAdapter(var context: Context)
    : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    var imageList: ArrayList<Documents> = arrayListOf()
    val requestOptions = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .override(1000, 1000)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(imageList.get(position).image_url)
            .apply(requestOptions)
            .fitCenter()
            .into(holder.imageView)
    }


    fun addList(list: ArrayList<Documents>) {
        imageList.addAll(list)
    }

    fun clearList(){
        imageList.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.image_item)
        }
    }
}