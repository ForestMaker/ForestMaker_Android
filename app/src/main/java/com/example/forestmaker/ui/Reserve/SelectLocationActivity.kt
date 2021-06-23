package com.example.forestmaker.ui.Reserve

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.LocationData
import com.example.forestmaker.ui.Reserve.Experience.ExperienceActivity
import kotlinx.android.synthetic.main.activity_select_location.*


class SelectLocationActivity : AppCompatActivity() {

    var locationDatas = mutableListOf<LocationData>()
    lateinit var locationAdapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_location)

        if (intent.getIntExtra("title", 0) == 1) {
            act_select_location_title.text = "나무 심기"
        } else {
            act_select_location_title.text = "체험하기"
        }

        act_select_location_btn_back.setOnClickListener {
            finish()
        }

        locationAdapter = LocationAdapter(
            this,
            object :LocationViewHolder.onClickListener{
                override fun onClickItem(position: Int) {
                    if (intent.getIntExtra("title", 0) == 1) {
                        // tung
                        val dialogView: View = layoutInflater.inflate(R.layout.activity_popup, null)

                        val builder = AlertDialog.Builder(this@SelectLocationActivity, R.style.MaterialAlertDialog_rounded)

                        dialogView.setBackgroundResource(android.R.color.transparent)
                        builder.setView(dialogView)

                        val alertDialog = builder.create()

                        alertDialog.show()
                        alertDialog.window!!.setLayout(900, 1300)




//                        val intentPlanting = Intent(this@SelectLocationActivity, PopupActivity::class.java)
//                        startActivity(intentPlanting)
                    } else {
                        val intentExperience = Intent(this@SelectLocationActivity, ExperienceActivity::class.java)
                        startActivity(intentExperience)
                    }

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

    fun OnClickHandler(view: View?) {
        val dialogView: View = layoutInflater.inflate(R.layout.activity_popup, null)
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}