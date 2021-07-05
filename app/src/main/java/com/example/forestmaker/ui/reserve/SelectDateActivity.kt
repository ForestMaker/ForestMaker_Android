package com.example.forestmaker.ui.reserve

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.DatePicker.OnDateChangedListener
import androidx.appcompat.app.AppCompatActivity
import com.example.forestmaker.R
import com.example.forestmaker.ui.reserve.Store.StoreActivity
import kotlinx.android.synthetic.main.activity_select_date.*


class SelectDateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_date)

        act_select_date_btn_close.setOnClickListener {
            finish()
        }

        act_select_date_btn_plus.setOnClickListener {
            act_select_date_txt_number.text = (act_select_date_txt_number.text.toString().toInt() + 1).toString()
        }

        act_select_date_btn_minus.setOnClickListener {
            act_select_date_txt_number.text = (act_select_date_txt_number.text.toString().toInt() - 1).toString()
        }


        act_select_date_btn_store.setOnClickListener {
            val intent = Intent(this, StoreActivity::class.java)

            intent.putExtra("month", act_select_date_datepicker.month.toString())
            intent.putExtra("day", act_select_date_datepicker.dayOfMonth.toString())
            intent.putExtra("hour", act_select_date_timepicker.hour.toString())
            intent.putExtra("minute", act_select_date_timepicker.minute.toString())
            intent.putExtra("peopleNumber", act_select_date_txt_number.text)

            startActivity(intent)
        }

        initMonthPicker()
        act_select_date_timepicker.setIs24HourView(true)
    }

    fun initMonthPicker() {
        val dp_mes = findViewById<DatePicker>(R.id.act_select_date_datepicker)
        val year: Int = dp_mes.year
        val month: Int = dp_mes.month
        val day: Int = dp_mes.dayOfMonth
        dp_mes.init(year, month, day,
            OnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
                val month_i = monthOfYear + 1
//                Log.e("selected month:", month_i.toString())
                //Add whatever you need to handle Date changes
                val day_i = dayOfMonth + 1
//                Log.e("selected day:", day_i.toString())
            })
        val daySpinnerId: Int = Resources.getSystem().getIdentifier("day", "id", "android")
        if (daySpinnerId != 0) {
            val daySpinner: View = dp_mes.findViewById(daySpinnerId)
            daySpinner.visibility = View.VISIBLE
        }
        val monthSpinnerId: Int = Resources.getSystem().getIdentifier("month", "id", "android")
        if (monthSpinnerId != 0) {
            val monthSpinner: View = dp_mes.findViewById(monthSpinnerId)
            monthSpinner.visibility = View.VISIBLE
        }
        val yearSpinnerId: Int = Resources.getSystem().getIdentifier("year", "id", "android")
        if (yearSpinnerId != 0) {
            val yearSpinner: View = dp_mes.findViewById(yearSpinnerId)
            yearSpinner.visibility = View.GONE
        }
    }
}