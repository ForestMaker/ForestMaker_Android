package com.example.forestmaker.ui.reserve

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.LocationData
import com.example.forestmaker.server.RequestToServer
import com.example.forestmaker.server.data.SelectLocationResponse
import com.example.forestmaker.ui.reserve.Experience.ExperienceActivity
import com.example.forestmaker.ui.reserve.Planting.LocationInfoActivity
import com.example.forestmaker.ui.reserve.Planting.SelectTreeActivity
import kotlinx.android.synthetic.main.activity_select_location.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
                        val intentPlanting = Intent(this@SelectLocationActivity, SelectTreeActivity::class.java)
                        intentPlanting.putExtra("type", "나무")
                        intentPlanting.putExtra("address", locationDatas[position].address)
                        intentPlanting.putExtra("name", locationDatas[position].name)

                        startActivity(intentPlanting)
                    } else {
                        val intentExperience = Intent(this@SelectLocationActivity, ExperienceActivity::class.java)
                        startActivity(intentExperience)
                    }

                }
            }
        )

        act_select_location_recyclerview.adapter = locationAdapter
        act_select_location_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        act_select_location_edit_search.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                // 엔터 눌렀을때 행동
                RequestToServer.service.reqeustLocation(act_select_location_edit_search.text.toString()).enqueue(object :Callback<SelectLocationResponse> {
                    override fun onResponse(
                        call: Call<SelectLocationResponse>,
                        response: Response<SelectLocationResponse>
                    ) {
                        if (response.isSuccessful) {
                            // 통신 완료 코드
                        }
                    }

                    override fun onFailure(call: Call<SelectLocationResponse>, t: Throwable) {
                        Log.e("error", t.message.toString())
                    }

                })
            }

            true
        }

        loadData()
    }

    fun loadData() {
        locationDatas.apply {
            add(
                LocationData(
                    "검산리 산 나무 ",
                    "강원도 홍천군 서석면 검산리 산 102"
                )
            )
            add(
                LocationData(
                    "어론리 산 나무 ",
                    "강원도 홍천군 서석면 어론리 산 132-1"
                )
            )
            add(
                LocationData(
                    "속초리 산 나무 ",
                    "강원도 홍천군 동면 속초리 산 271"
                )
            )
            add(
                LocationData(
                    "와야리 산 나무 ",
                    "강원도 홍천군 내촌면 와야리 산 12-1"
                )
            )
            add(
                LocationData(
                    "유포리 산 나무 ",
                    "강원도 평찬군 봉평면 유포리 산 200"
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