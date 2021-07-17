package com.forest.forestmaker.ui.home

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
import com.forest.forestmaker.R
import com.forest.forestmaker.data.MyTreeData

class MyTreeAdapter (private val context: Context, private val clickListener: MytreeViewHolder.onClickListener) : RecyclerView.Adapter<MytreeViewHolder>(){

    var datas = mutableListOf<MyTreeData>()
    var previousPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MytreeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_mytree, parent, false)
        return MytreeViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: MytreeViewHolder, position: Int) {
        holder.bind(datas[position])
        previousPosition = position
    }

}


class MytreeViewHolder(itemview: View, clickListener: onClickListener) : RecyclerView.ViewHolder(itemview) {

    val mytreeImage = itemview.findViewById<ImageView>(R.id.item_mytree_img)
    val mytreeName = itemview.findViewById<TextView>(R.id.item_mytree_txt_name)
    val mytreeDate = itemview.findViewById<TextView>(R.id.item_mytree_txt_date)
    val mytreeLocation = itemview.findViewById<TextView>(R.id.item_mytree_txt_location)

    fun bind(myTreeData: MyTreeData) {
        Glide.with(itemView).load(myTreeData.mytreeImg).apply(
            RequestOptions().transforms(
                CenterCrop(),
                RoundedCorners(25)
            )).into(mytreeImage)

        mytreeName.text = myTreeData.mytreeName
        mytreeDate.text = myTreeData.mytreeDate
        mytreeLocation.text = myTreeData.mytreeLocation
    }

    init {
        itemview.setOnClickListener {
            clickListener.onClickItem(adapterPosition)
        }
    }

    interface onClickListener {
        fun onClickItem(position: Int)
    }

}