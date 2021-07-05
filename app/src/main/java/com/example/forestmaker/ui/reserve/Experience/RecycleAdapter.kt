package com.example.forestmaker.ui.reserve.Experience

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.forestmaker.R
import com.example.forestmaker.data.BannerData

class RecycleAdapter (private val context:Context, private val onClickListener: RecycleViewHolder.OnClickListener): RecyclerView.Adapter<RecycleViewHolder>() {

    var datas = mutableListOf<BannerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_experience_recycler, parent, false)
        return RecycleViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}

class RecycleViewHolder (itemView: View, onClickListener: OnClickListener): RecyclerView.ViewHolder(itemView) {

    val recycleImg = itemView.findViewById<ImageView>(R.id.item_experience_img)
    var recycleTxt = itemView.findViewById<TextView>(R.id.item_experience_txt_title)

    fun bind(bannerData: BannerData) {
        Glide.with(itemView).load(bannerData.bannerImg).apply(
            RequestOptions().transforms(
                CenterCrop(),
                RoundedCorners(30)
            )).into(recycleImg)

        recycleTxt.text = bannerData.bannerTitle
    }

    init {
        itemView.setOnClickListener {
            onClickListener.clickItem(adapterPosition)
        }
    }
    interface OnClickListener{
        fun clickItem(position: Int)
    }
}