package com.forest.forestmaker.ui.reserve.Experience

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.forest.forestmaker.R

class ExperienceOptionAdapter (private val context: Context): RecyclerView.Adapter<ExperienceOptionViewHolder>() {

    var datas = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceOptionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_experience_forestschool_option, parent, false)
        return ExperienceOptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExperienceOptionViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}

class ExperienceOptionViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {

    val option = itemview.findViewById<TextView>(R.id.item_forestSchool_option_txt)

    fun bind(optionText: String){
        option.text = optionText
    }

    init {
        itemview.setOnClickListener {
            itemview.isSelected = !itemview.isSelected
            if (itemview.isSelected) {
                itemview.findViewById<ImageView>(R.id.item_forestSchool_option_check).visibility = View.VISIBLE
            } else {
                itemview.findViewById<ImageView>(R.id.item_forestSchool_option_check).visibility = View.INVISIBLE
            }
        }
    }
}