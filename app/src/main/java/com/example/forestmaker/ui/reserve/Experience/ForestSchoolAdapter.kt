package com.example.forestmaker.ui.reserve.Experience

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.forestmaker.R
import com.example.forestmaker.data.BannerData

class ForestSchoolAdapter (private val context:Context, private val onClickListener: ForestSchoolViewHolder.OnClickListener): RecyclerView.Adapter<ForestSchoolViewHolder>() {

    var datas = mutableListOf<BannerData>()

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ForestSchoolViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForestSchoolViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_experience_recycler, parent, false)
        return ForestSchoolViewHolder(view, onClickListener)
    }
}

class ForestSchoolViewHolder (itemView: View, onClickListener: OnClickListener): RecyclerView.ViewHolder(itemView) {

    val recycleImg = itemView.findViewById<ImageView>(R.id.item_experience_img)

    fun bind(bannerData: BannerData) {
        Glide.with(itemView).load(bannerData.bannerImg).apply(
            RequestOptions().transforms(
                CenterCrop(),
                RoundedCorners(30)
            )).into(recycleImg)
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