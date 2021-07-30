package com.forest.forestmaker.ui.reserve

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.forest.forestmaker.R

class ReserveBannerAdapter(private val context: Context, private val onClickListener: ReserveBannerViewHolder.OnClickListener): RecyclerView.Adapter<ReserveBannerViewHolder>(){

    private var locationData = mutableListOf(
        R.drawable.location_gangwon,
        R.drawable.location_gyeonggi,
        R.drawable.location_gyeongsang,
        R.drawable.location_jeolla
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReserveBannerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_reserve_banner, parent, false)
        return ReserveBannerViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: ReserveBannerViewHolder, position: Int) {
        holder.bind(locationData[position])
    }

    override fun getItemCount(): Int {
        return locationData.size
    }

}

class ReserveBannerViewHolder(itemview: View, onClickListener: OnClickListener): RecyclerView.ViewHolder(itemview) {

    val locationImg: ImageView = itemview.findViewById(R.id.item_reserve_banner_img)

    fun bind(locationData: Int) {
        Glide.with(itemView).load(locationData).into(locationImg)
    }

    init {
        itemview.setOnClickListener {
            onClickListener.onClickBanner(adapterPosition)
        }
    }

    interface OnClickListener{
        fun onClickBanner(position: Int)
    }
}
