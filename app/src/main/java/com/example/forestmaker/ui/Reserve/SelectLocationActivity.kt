package com.example.forestmaker.ui.Reserve

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forestmaker.R
import com.example.forestmaker.data.LocationData
import kotlinx.android.synthetic.main.activity_select_location.*

class SelectLocationActivity : AppCompatActivity() {

    var locationDatas = mutableListOf<LocationData>()
    lateinit var locationAdapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_location)

        locationAdapter = LocationAdapter(
            this,
            object :LocationViewHolder.onClickListener{
                override fun onClickItem(position: Int) {
                    val intent = Intent(this@SelectLocationActivity, SelectDateActivity::class.java)
                    startActivity(intent)
                }
            }
        )

        act_select_location_recyclerview.adapter = locationAdapter
        act_select_location_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        loadData()
    }

    fun loadData() {
        locationDatas.apply {
            add(
                LocationData(
                    "마곡나루역 근처 나무 ",
                    "주소주소-123"
                )
            )
            add(
                LocationData(
                    "마곡나루역 근처 나무 ",
                    "주소주소-123"
                )
            )
            add(
                LocationData(
                    "마곡나루역 근처 나무 ",
                    "주소주소-123"
                )
            )
            add(
                LocationData(
                    "마곡나루역 근처 나무 ",
                    "주소주소-123"
                )
            )
            add(
                LocationData(
                    "마곡나루역 근처 나무 ",
                    "주소주소-123"
                )
            )
            add(
                LocationData(
                    "마곡나루역 근처 나무 ",
                    "주소주소-123"
                )
            )
            add(
                LocationData(
                    "마곡나루역 근처 나무 ",
                    "주소주소-123"
                )
            )
            add(
                LocationData(
                    "마곡나루역 근처 나무 ",
                    "주소주소-123"
                )
            )

        }

        locationAdapter.datas = locationDatas
        locationAdapter.notifyDataSetChanged()
    }
}