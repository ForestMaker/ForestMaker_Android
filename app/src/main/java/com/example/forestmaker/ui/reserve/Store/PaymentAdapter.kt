package com.example.forestmaker.ui.reserve.Store

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forestmaker.R
import com.example.forestmaker.data.ShoppingCartData

class PaymentAdapter (private val context: Context): RecyclerView.Adapter<PaymentViewHolder>() {

    var datas = ArrayList<ShoppingCartData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_payment_list, parent, false)
        return PaymentViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}

class PaymentViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {

    val itemName = itemview.findViewById<TextView>(R.id.item_txt_itemName)
    val itemPrice = itemview.findViewById<TextView>(R.id.item_txt_itemPrice)
    val itemNumber = itemview.findViewById<TextView>(R.id.item_txt_itemNumber)

    fun bind(shoppingCartData: ShoppingCartData) {
        itemName.text = shoppingCartData.itemName
        itemPrice.text = (shoppingCartData.itemPrice_int * shoppingCartData.itemNumber).toString()
        itemNumber.text = shoppingCartData.itemNumber.toString()
    }
}