package com.example.forestmaker.ui.home.mileage

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.forestmaker.R
import com.example.forestmaker.data.MileageInfoData

class MileageAdapter(private val context: Context) : RecyclerView.Adapter<MileageViewHolder>() {

    var datas = mutableListOf<MileageInfoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MileageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_mileage_list, parent, false)
        return MileageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MileageViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}

class MileageViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {

    val use_or_save = itemview.findViewById<ImageView>(R.id.item_mileage_img)
    val point = itemview.findViewById<TextView>(R.id.item_mileage_mileage)
    val date = itemview.findViewById<TextView>(R.id.item_mileage_dateTime)
    val headcount = itemview.findViewById<TextView>(R.id.item_mileage_headcount)
    val address = itemview.findViewById<TextView>(R.id.item_mileage_address)
    val name = itemview.findViewById<TextView>(R.id.item_mileage_name)

    fun bind(mileageInfoData: MileageInfoData) {
        use_or_save.isSelected = mileageInfoData.type

        if (use_or_save.isSelected) {
            point.text = "-" + mileageInfoData.point
            point.setTextColor(Color.parseColor("#ff6f6f"))
        } else {
            point.text = "+" + mileageInfoData.point
            point.setTextColor(Color.parseColor("#31805e"))
        }

        date.text = mileageInfoData.dateTime
        headcount.text = mileageInfoData.headCount
        address.text = mileageInfoData.location
        name.text = mileageInfoData.name

    }

}