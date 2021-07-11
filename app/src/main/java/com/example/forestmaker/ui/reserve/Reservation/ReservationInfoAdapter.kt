package com.example.forestmaker.ui.reserve.Reservation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forestmaker.R
import com.example.forestmaker.data.ReservationData
import kotlinx.android.synthetic.main.item_reservation_list.view.*

class ReservationAdapter (private val context: Context, private val onClickListener: ReservationViewHolder.OnClickListener):RecyclerView.Adapter<ReservationViewHolder>(){

    var datas = mutableListOf<ReservationData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_reservation_list, parent, false)
        return ReservationViewHolder(view, onClickListener)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        holder.bind(datas[position])
    }

}

class ReservationViewHolder(itemview: View, onClickListener: OnClickListener): RecyclerView.ViewHolder(itemview){

    val dateTime = itemview.findViewById<TextView>(R.id.item_reservation_dateTime)
    val peopleNum = itemview.findViewById<TextView>(R.id.item_reservation_peopleNum)
    val treeName = itemview.findViewById<TextView>(R.id.item_reservation_treeName)
    val location = itemview.findViewById<TextView>(R.id.item_reservation_location)
    val type = itemview.findViewById<TextView>(R.id.item_reservation_type)

    fun bind(reservationData: ReservationData){
        dateTime.text = reservationData.dateTime
        peopleNum.text = reservationData.headCount
        treeName.text = reservationData.name
        location.text = reservationData.location
        type.text = "[" + reservationData.type + "]"
    }

    init {
        itemview.item_reservation_btn.setOnClickListener {
            onClickListener.ReservationDetailClick(adapterPosition)
        }
    }

    interface OnClickListener{
        fun ReservationDetailClick(position: Int)
    }
}