package com.forest.forestmaker.ui.reserve


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.forest.forestmaker.R
import com.forest.forestmaker.data.LocationData
import com.forest.forestmaker.server.RequestToServer
import com.forest.forestmaker.server.data.FindLocationResponse
import com.forest.forestmaker.ui.reserve.Experience.ExperienceActivity
import com.forest.forestmaker.ui.reserve.Planting.SelectTreeActivity
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
    private var userEmail = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_location)

        setIntentData()
        setButton()
        setAdapter()
    }

    private fun setIntentData() {
        userEmail = intent.getStringExtra("user_email").toString()

        if (intent.getIntExtra("title", 0) == 1) {
            act_select_location_title.text = "나무 심기"
        } else {
            act_select_location_title.text = "체험하기"
        }
    }

    private fun setButton() {
        act_select_location_btn_back.setOnClickListener { finish() }

        act_select_location_edit_search.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER) {
                // 엔터 눌렀을때 행동
                val imm: InputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(act_select_location_edit_search.windowToken, 0)

                getTreeData()
                true
            } else {
                false
            }

        }
    }

    private fun setAdapter(){
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
                        intentPlanting.putExtra("user_email", userEmail)
                        startActivity(intentPlanting)
                    } else {
                        val intentExperience = Intent(this@SelectLocationActivity, ExperienceActivity::class.java)
                        intentExperience.putExtra("user_email", userEmail)
                        startActivity(intentExperience)
                    }
                }
            }
        )

        act_select_location_recyclerview.adapter = locationAdapter
        act_select_location_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    private fun getTreeData() {
        locationDatas.clear()
        RequestToServer.service.requestTreeLocation(JsonParser.parseString(JSONObject().put("location", act_select_location_edit_search.text.toString()).toString()) as JsonObject).enqueue(object :Callback<ArrayList<FindLocationResponse>> {
            override fun onResponse(
                call: Call<ArrayList<FindLocationResponse>>,
                response: Response<ArrayList<FindLocationResponse>>
            ) {
                if (response.isSuccessful) {
                    // 통신 완료 코드
                    Log.e("success to get treeLocation", response.body().toString())
                    for (item in response.body()!!) {
                        locationDatas.apply {
                            add(
                                LocationData(
                                    type = item.type,
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
                Log.e("fail to get treeLocation", t.message.toString())
            }

        })
    }

}