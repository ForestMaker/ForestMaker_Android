package com.forest.forestmaker.ui.reserve.Planting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.forest.forestmaker.R
import kotlinx.android.synthetic.main.activity_location_info.*

class LocationInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_info)

        act_location_info_linear.setOnClickListener {
            val intent = Intent(this, SelectTreeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}