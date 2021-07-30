package com.forest.forestmaker.ui.home.mileage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.forest.forestmaker.R
import com.forest.forestmaker.data.MileageInfoData
import com.forest.forestmaker.server.RequestToServer
import com.forest.forestmaker.server.data.MileageResponse
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_mileage.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MileageActivity : AppCompatActivity() {

    lateinit var mileageAdapter: MileageAdapter
    lateinit var mileageData: MileageInfoData
    var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mileage)

        setIntentData()
        setButton()
        getMileage()
        
    }

    private fun setIntentData() {
        email = intent.getStringExtra("user_email").toString()
    }

    private fun setButton() {
        act_mileage_btn_back.setOnClickListener { finish() }
    }

    private fun setAdapter() {
        mileageAdapter = MileageAdapter(this)
        act_mileage_recyclerview.adapter = mileageAdapter
        act_mileage_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun getMileage() {

        val email = JsonParser.parseString(JSONObject().put("userid", email).toString()) as JsonObject

        RequestToServer.service.requestMileage(email).enqueue(object :Callback<ArrayList<MileageResponse>> {
            override fun onResponse(
                call: Call<ArrayList<MileageResponse>>,
                response: Response<ArrayList<MileageResponse>>
            ) {
                if (response.isSuccessful) {

                    Log.e("success", response.body().toString())

                    val list = mutableListOf<MileageInfoData>()

                    for (mileage in response.body()!!) {
                        list.apply {
                            add(
                                MileageInfoData(
                                    type = mileage.type,
                                    point = mileage.mileage,
                                    dateTime = mileage.date,
                                    headCount = mileage.headcount,
                                    name = mileage.item,
                                    location =  mileage.address
                                )
                            )
                        }
                    }

                    mileageAdapter.datas = list
                    mileageAdapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<ArrayList<MileageResponse>>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }

        })
    }
}