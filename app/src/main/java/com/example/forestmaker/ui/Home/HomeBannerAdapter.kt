package com.example.forestmaker.ui.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.forestmaker.R
import com.example.forestmaker.data.BannerData

class HomeBannerAdapter(private val context: FragmentActivity?, private val onClickListener: HomeBannerViewHolder.OnClickListener): RecyclerView.Adapter<HomeBannerViewHolder>(){

    var datas = mutableListOf<BannerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_home_banner, parent, false)
        return HomeBannerViewHolder(view, onClickListener)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}

class HomeBannerViewHolder(itemview:View, onClickListener: OnClickListener): RecyclerView.ViewHolder(itemview){

    val homeBannerImg = itemview.findViewById<ImageView>(R.id.item_home_banner_img)
    val homeBannerTitle = itemview.findViewById<TextView>(R.id.item_home_banner_txt_title)

    fun bind(bannerData: BannerData){

        Glide.with(itemView).load(bannerData.bannerImg).apply(
            RequestOptions().transforms(
                CenterCrop(),
                RoundedCorners(30)
            )).into(homeBannerImg)

        homeBannerTitle.text = bannerData.bannerTitle
    }

    init {
        itemview.setOnClickListener {
            onClickListener.onBannerClick(adapterPosition)
        }
    }

    interface OnClickListener{
        fun onBannerClick(position: Int)
    }
}