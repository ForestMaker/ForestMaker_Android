package com.forest.forestmaker.ui.reserve

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.forest.forestmaker.R
import com.forest.forestmaker.data.LocationData

class LocationAdapter(private val context: Context, private val onClickListener: LocationViewHolder.onClickListener): RecyclerView.Adapter<LocationViewHolder>(){

    var datas = mutableListOf<LocationData>()
    var previousPosition = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_text_underbar, parent, false)
        return LocationViewHolder(view, onClickListener)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(datas[position])
        previousPosition = position
    }

}

class LocationViewHolder(itemView: View, clickListener: onClickListener): RecyclerView.ViewHolder(itemView){

    val type = itemView.findViewById<TextView>(R.id.item_txt_type)
    val name = itemView.findViewById<TextView>(R.id.item_txt_name)
    val address = itemView.findViewById<TextView>(R.id.item_txt_address)

    fun bind(locationData: LocationData){
        if (locationData.type.isBlank()){
            type.text = ""
        } else {
            type.text = "[" + locationData.type + "] "
        }
        name.text = locationData.name
        address.text = locationData.address
    }

    init {
        itemView.setOnClickListener {
            clickListener.onClickItem(adapterPosition)
        }

    }

    interface onClickListener {
        fun onClickItem(position: Int)
    }
}