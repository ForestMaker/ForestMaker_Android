package com.forest.forestmaker.ui.reserve.Store

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
import com.forest.forestmaker.data.StoreData
import kotlinx.android.synthetic.main.item_store_item.view.*


class StoreItemAdapter(
    private val context: Context?,
    private val dataList: MutableList<StoreData>,
    private val itemWidth: Int,
    private val clickListener: RecyclerViewHolder.OnClickListener
) :
    RecyclerView.Adapter<StoreItemAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_store_item, parent, false)
        return RecyclerViewHolder(view, itemWidth, clickListener)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindData(dataList!![position])
    }

    override fun getItemCount(): Int {
        return dataList.size ?: 0
    }

    class RecyclerViewHolder(itemView: View, itemWidth: Int, onClickListener: OnClickListener) : RecyclerView.ViewHolder(itemView) {

        init {
            val layoutParams: ViewGroup.LayoutParams = itemView.layoutParams
            layoutParams.width = itemWidth

            itemView.item_store_btn_plus.setOnClickListener {
                itemNumber.text = (itemNumber.text.toString().toInt()+1).toString()
                onClickListener.onPlusItem(adapterPosition)
            }

            itemView.item_store_btn_minus.setOnClickListener {
                itemNumber.text = (itemNumber.text.toString().toInt()-1).toString()
                onClickListener.onMinusItem(adapterPosition)
            }

            itemView.item_store_btn_cart.setOnClickListener {
                onClickListener.onItemCartClick(adapterPosition)
            }

        }


        val itemImg = itemView.findViewById<ImageView>(R.id.item_store_img)
        val itemName = itemView.findViewById<TextView>(R.id.item_store_txt_itemName)
        val itemPrice = itemView.findViewById<TextView>(R.id.item_store_txt_itemPrice)
        val itemNumber = itemView.findViewById<TextView>(R.id.item_store_txt_num)


        fun bindData(itemData: StoreData) {
            itemView.visibility = View.VISIBLE
            Glide.with(itemView).load(itemData.itemImg).apply(
                RequestOptions().transforms(
                    CenterCrop(),
                    RoundedCorners(13)
                )).into(itemImg)

            itemName.text = itemData.itemName
            itemPrice.text = itemData.itemPrice
            itemNumber.text = itemData.itemNumber.toString()
        }


        interface OnClickListener{
            fun onItemCartClick(position:Int)
            fun onPlusItem(position: Int)
            fun onMinusItem(position: Int)
        }
    }
}