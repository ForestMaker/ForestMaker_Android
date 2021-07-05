package com.example.forestmaker.ui.reserve

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forestmaker.R
import com.example.forestmaker.data.ShoppingCartData
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

    val itemName = itemview.findViewById<TextView>(R.id.item_txt_name)
    val itemPrice = itemView.findViewById<TextView>(R.id.item_txt_address)

    fun bind(shoppingCartData: ShoppingCartData){
        itemName.text = shoppingCartData.itemName
        itemPrice.text = shoppingCartData.itemPrice
    }

    init {
        itemview.item_btn_delete.setOnClickListener {
            clickListener.onClickItemDelete(adapterPosition)
        }
    }

    interface onClickListener{
        fun onClickItemDelete(position: Int)
    }
}