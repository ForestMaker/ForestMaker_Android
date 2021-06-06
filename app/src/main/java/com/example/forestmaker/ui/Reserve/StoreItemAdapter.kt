package com.example.forestmaker.ui.Reserve

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
import com.example.forestmaker.data.StoreItemData
import kotlinx.android.synthetic.main.item_store_item.view.*

class StoreItemAdapter(private val context: Context, private val clickListener: StoreItemViewHolder.onClickListener): RecyclerView.Adapter<StoreItemViewHolder>(){

    var datas = mutableListOf<StoreItemData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_store_item, parent, false)
        return StoreItemViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}

class StoreItemViewHolder(itemView: View, clickListener: onClickListener): RecyclerView.ViewHolder(itemView){

    val itemImg = itemView.findViewById<ImageView>(R.id.item_store_img)
    val itemName = itemView.findViewById<TextView>(R.id.item_store_txt_itemName)
    val itemPrice = itemView.findViewById<TextView>(R.id.item_store_txt_itemPrice)
    val itemNumber = itemView.findViewById<TextView>(R.id.item_store_txt_num)

    fun bind(storeItemData: StoreItemData){
        Glide.with(itemView).load(storeItemData.itemImg).apply(
            RequestOptions().transforms(
                CenterCrop(),
                RoundedCorners(13)
            )).into(itemImg)

        itemName.text = storeItemData.itemName
        itemPrice.text = storeItemData.itemPrice

    }
    init {
        itemView.item_store_btn_plus.setOnClickListener {
            itemNumber.text = (itemNumber.text.toString().toInt()+1).toString()
        }

        itemView.item_store_btn_minus.setOnClickListener {
            itemNumber.text = (itemNumber.text.toString().toInt()-1).toString()
        }

        itemView.item_store_btn_cart.setOnClickListener {
            clickListener.onItemCartClick(adapterPosition)
        }
    }

    interface onClickListener{
//        fun onItemPlusClick(position:Int)
//        fun onItemMinusClick(position:Int)
        fun onItemCartClick(position:Int)
    }
}