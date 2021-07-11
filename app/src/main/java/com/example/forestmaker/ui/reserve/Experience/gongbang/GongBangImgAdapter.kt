package com.example.forestmaker.ui.reserve.Experience.gongbang

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
import com.example.forestmaker.R

class GongBangImgAdapter(private val context: FragmentActivity?): RecyclerView.Adapter<GongBangImgViewHolder>(){

    var datas = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GongBangImgViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_gongbang_img, parent, false)
        return GongBangImgViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: GongBangImgViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}

class GongBangImgViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){

    val image = itemview.findViewById<ImageView>(R.id.item_gongbang_img)

    fun bind(imgList: String){

        Glide.with(itemView).load(imgList).apply(
            RequestOptions().transforms(
                CenterCrop(),
                RoundedCorners(30)
            )).into(image)
    }
}