package com.example.forestmaker.ui.reserve


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.LocationData
import com.example.forestmaker.server.RequestToServer
import com.example.forestmaker.server.data.FindLocationResponse
import com.example.forestmaker.ui.reserve.Experience.ExperienceActivity
import com.example.forestmaker.ui.reserve.Planting.SelectTreeActivity
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_select_location.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SelectLocationActivity : AppCompatActivity() {

    var locationDatas = mutableListOf<LocationData>()
    lateinit var locationAdapter: LocationAdapter
    var user_email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_location)

        user_email = intent.getStringExtra("user_email").toString()

        if (intent.getIntExtra("title", 0) == 1) {
            act_select_location_title.text = "나무 심기"
        } else {
            act_select_location_title.text = "체험하기"
        }

        act_select_location_btn_back.setOnClickListener { finish() }

        locationAdapter = LocationAdapter(
            this,
            object :LocationViewHolder.onClickListener{
                override fun onClickItem(position: Int) {
                    if (intent.getIntExtra("title", 0) == 1) {
                        val intentPlanting = Intent(this@SelectLocationActivity, SelectTreeActivity::class.java)
                        intentPlanting.putExtra("type", "나무")
                        intentPlanting.putExtra("address", locationDatas[position].address)
                        intentPlanting.putExtra("name", locationDatas[position].name)
                        intentPlanting.putExtra("location_trees", locationDatas[position].trees)
                        intentPlanting.putExtra("user_email", user_email)
                        startActivity(intentPlanting)
                        finish()
                    } else {
                        val intentExperience = Intent(this@SelectLocationActivity, ExperienceActivity::class.java)
                        intentExperience.putExtra("user_email", user_email)
                        startActivity(intentExperience)
                        finish()

                    }

                }
            }
        )

        act_select_location_recyclerview.adapter = locationAdapter
        act_select_location_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        act_select_location_edit_search.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                // 엔터 눌렀을때 행동
                RequestToServer.service.requestTreeLocation(JsonParser.parseString(JSONObject().put("location", act_select_location_edit_search.text.toString()).toString()) as JsonObject).enqueue(object :Callback<ArrayList<FindLocationResponse>> {
                    override fun onResponse(
                        call: Call<ArrayList<FindLocationResponse>>,
                        response: Response<ArrayList<FindLocationResponse>>
                    ) {
                        if (response.isSuccessful) {
                            // 통신 완료 코드
                            for (item in response.body()!!) {
                                locationDatas.apply {
                                    add(
                                        LocationData(
                                            name = item.name,
                                            address = item.address,
                                            trees = item.trees
                                        )
                                    )
                                }
                            }

                            locationAdapter.datas = locationDatas
                            locationAdapter.notifyDataSetChanged()

                        }
                    }

                    override fun onFailure(
                        call: Call<ArrayList<FindLocationResponse>>,
                        t: Throwable
                    ) {
                        Log.e("fail to get treelocation", t.message.toString())
                    }

                })
            }

            true
        }

    }

}