package com.forest.forestmaker.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.forest.forestmaker.R
import com.forest.forestmaker.data.BannerData

class HomeBannerAdapter(private val context: FragmentActivity?, private val onclickListener: HomeBannerViewHolder.OnClickListener): RecyclerView.Adapter<HomeBannerViewHolder>(){

    var datas = mutableListOf<BannerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_home_banner, parent, false)
        return HomeBannerViewHolder(view, onclickListener)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}

class HomeBannerViewHolder(itemview:View, onclickListener: OnClickListener): RecyclerView.ViewHolder(itemview){

    val homeBannerImg = itemview.findViewById<ImageView>(R.id.item_banner_img)

    fun bind(bannerData: BannerData){

        Glide.with(itemView).load(bannerData.bannerImg).apply(
            RequestOptions().transforms(
                CenterCrop(),
                RoundedCorners(30)
            )).into(homeBannerImg)
    }

    init {
        itemview.setOnClickListener {
            onclickListener.onClickBanner(adapterPosition)
        }
    }

    interface OnClickListener{
        fun onClickBanner(position: Int)
    }
}