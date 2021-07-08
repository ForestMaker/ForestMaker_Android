package com.example.forestmaker.ui.reserve

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
import com.example.forestmaker.data.ShoppingCartData
import kotlinx.android.synthetic.main.item_store_item.view.*
import kotlinx.android.synthetic.main.item_store_shoppingcart.view.*

class ShoppingCartAdapter (private val context: Context, private val onClickListener: ShoppingCartViewHolder.onClickListener): RecyclerView.Adapter<ShoppingCartViewHolder>(){

    var datas = ArrayList<ShoppingCartData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_store_shoppingcart, parent, false)
        return ShoppingCartViewHolder(view, onClickListener)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ShoppingCartViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}

class ShoppingCartViewHolder(itemview: View, clickListener: onClickListener): RecyclerView.ViewHolder(itemview) {

    init {

        itemview.item_shoppingcart_btn_delete.setOnClickListener {
            clickListener.onClickItemDelete(adapterPosition)
        }

        itemview.item_shoppingcart_btn_plus.setOnClickListener {
            itemNum.text = (itemNum.text.toString().toInt()+1).toString()
//            onClickListener.onPlusItem(adapterPosition)
        }

        itemview.item_shoppingcart_btn_minus.setOnClickListener {
            itemNum.text = (itemNum.text.toString().toInt()-1).toString()
//            onClickListener.onMinusItem(adapterPosition)
        }
    }

    val itemName = itemview.findViewById<TextView>(R.id.item_shoppingcart_txt_name)
    val itemPrice = itemview.findViewById<TextView>(R.id.item_shoppingcart_txt_price)
    val itemNum = itemview.findViewById<TextView>(R.id.item_shoppingcart_txt_num)
    val itemImg = itemview.findViewById<ImageView>(R.id.item_shoppingcart_img)

    interface onClickListener{
        fun onClickItemDelete(position: Int)
        fun onPlusItem(position: Int)
        fun onMinusItem(position: Int)

    }

    fun bind(shoppingCartData: ShoppingCartData){

        Glide.with(itemView).load(shoppingCartData.itemImg).apply(
            RequestOptions().transforms(
                CenterCrop(),
                RoundedCorners(13)
            )).into(itemImg)

        itemName.text = shoppingCartData.itemName
        itemPrice.text = shoppingCartData.itemPrice_str
        itemNum.text = shoppingCartData.itemNumber.toString()
    }




}