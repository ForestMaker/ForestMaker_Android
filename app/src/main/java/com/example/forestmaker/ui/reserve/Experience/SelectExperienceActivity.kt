package com.example.forestmaker.ui.reserve.Experience

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.ForestSchoolData
import com.example.forestmaker.data.LocationData
import com.example.forestmaker.server.RequestToServer
import com.example.forestmaker.server.data.ForestSchool
import com.example.forestmaker.ui.reserve.LocationAdapter
import com.example.forestmaker.ui.reserve.LocationViewHolder
import kotlinx.android.synthetic.main.activity_select_experience.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectExperienceActivity : AppCompatActivity() {

    var forestschoolDummy = ArrayList<ForestSchool>()
    lateinit var locationExperienceAdapter: LocationAdapter
    var user_email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_experience)

        user_email = intent.getStringExtra("user_email").toString()
        act_select_experience_location_btn_back.setOnClickListener {
            finish()
        }

        locationExperienceAdapter = LocationAdapter(this, object : LocationViewHolder.onClickListener{
            override fun onClickItem(position: Int) {

                val intent = Intent(this@SelectExperienceActivity, ExperienceActivity::class.java)
                intent.putExtra("forestschool", forestschoolDummy)
                intent.putExtra("position", position)
                intent.putExtra("user_email", user_email)
                startActivity(intent)
                finish()
            }

        })

        act_select_experience_location_recyclerview.adapter = locationExperienceAdapter
        act_select_experience_location_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        getForestSchoolData()

    }

    private fun getForestSchoolData() {

        RequestToServer.service.requestForestSchool().enqueue(object : Callback<ArrayList<ForestSchool>>{
            override fun onResponse(
                call: Call<ArrayList<ForestSchool>>,
                response: Response<ArrayList<ForestSchool>>
            ) {
                if (response.isSuccessful) {
                    val list = ArrayList<LocationData>()

                    for (item in response.body()!!) {
                        list.apply {
                            add(
                                LocationData(
                                    name = item.name,
                                    address = item.address
                                )
                            )
                        }
                        forestschoolDummy.apply {
                            add(
                                ForestSchool(
                                    name = item.name,
                                    address = item.address,
                                    hours = item.hours,
                                    runtime = item.runtime,
                                    participants = item.participants,
                                    fee = item.fee,
                                    fee_int = item.fee_int,
                                    info = item.info,
                                    image = item.image
                                )
                            )
                        }
                    }
                    locationExperienceAdapter.datas = list
                    locationExperienceAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ArrayList<ForestSchool>>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }
        })

    }

}