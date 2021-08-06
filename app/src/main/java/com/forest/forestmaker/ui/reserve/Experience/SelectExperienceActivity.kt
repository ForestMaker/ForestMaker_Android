package com.forest.forestmaker.ui.reserve.Experience

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.forest.forestmaker.R
import com.forest.forestmaker.data.BannerData
import com.forest.forestmaker.data.LocationData
import com.forest.forestmaker.server.RequestToServer
import com.forest.forestmaker.server.data.ForestSchool
import com.forest.forestmaker.server.data.GongBangResponse
import com.forest.forestmaker.ui.reserve.Experience.gongbang.GongBangActivity
import com.forest.forestmaker.ui.reserve.LocationAdapter
import com.forest.forestmaker.ui.reserve.LocationViewHolder
import kotlinx.android.synthetic.main.activity_select_experience.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectExperienceActivity : AppCompatActivity() {

    var forestSchoolDummy = ArrayList<ForestSchool>()
    var gongbangData = ArrayList<GongBangResponse>()
    var recycleData = mutableListOf<BannerData>()

    val forestSchoolList = ArrayList<LocationData>()
    val gongBangList = ArrayList<LocationData>()

    lateinit var locationExperienceAdapter: LocationAdapter
    var userEmail = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_experience)

        userEmail = intent.getStringExtra("user_email").toString()
        initialButton()
        setButton()
        setAdapter()
        getForestSchoolData()
        getGongBangData()

    }

    private fun initialButton() {
        act_select_experience_btn_forestSchool.isSelected = true
        act_select_experience_btn_gongBang.isSelected = false
    }

    private fun setButton() {
        act_select_experience_location_btn_back.setOnClickListener {
            finish()
        }

        act_select_experience_btn_forestSchool.setOnClickListener {
            if (act_select_experience_btn_forestSchool.isSelected) {
                return@setOnClickListener
            }
            act_select_experience_btn_forestSchool.isSelected =
                !act_select_experience_btn_forestSchool.isSelected
            act_select_experience_btn_gongBang.isSelected =
                !act_select_experience_btn_gongBang.isSelected
            changeData()
        }

        act_select_experience_btn_gongBang.setOnClickListener {
            if (act_select_experience_btn_gongBang.isSelected) {
                return@setOnClickListener
            }
            act_select_experience_btn_forestSchool.isSelected =
                !act_select_experience_btn_forestSchool.isSelected
            act_select_experience_btn_gongBang.isSelected =
                !act_select_experience_btn_gongBang.isSelected
            changeData()
        }
    }

    private fun setAdapter() {
        locationExperienceAdapter =
            LocationAdapter(this, object : LocationViewHolder.onClickListener {
                override fun onClickItem(position: Int) {

                    if (act_select_experience_btn_forestSchool.isSelected) {
                        val intent =
                            Intent(this@SelectExperienceActivity, ExperienceActivity::class.java)
                        intent.putExtra("forestschool", forestSchoolDummy)
                        intent.putExtra("position", position)
                        intent.putExtra("user_email", userEmail)
                        startActivity(intent)
                    } else {
                        val intent =
                            Intent(this@SelectExperienceActivity, GongBangActivity::class.java)
                        intent.putExtra("gongbangList", gongbangData)
                        intent.putExtra("position", position)
                        intent.putExtra("user_email", userEmail)
                        startActivity(intent)
                    }
                }

            })

        act_select_experience_location_recyclerview.adapter = locationExperienceAdapter
        act_select_experience_location_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun changeData() {
        if (act_select_experience_btn_forestSchool.isSelected) {
            locationExperienceAdapter.datas = forestSchoolList
            locationExperienceAdapter.notifyDataSetChanged()
        } else {
            locationExperienceAdapter.datas = gongBangList
            locationExperienceAdapter.notifyDataSetChanged()
        }
    }

    private fun getForestSchoolData() {

        RequestToServer.service.requestForestSchool()
            .enqueue(object : Callback<ArrayList<ForestSchool>> {
                override fun onResponse(
                    call: Call<ArrayList<ForestSchool>>,
                    response: Response<ArrayList<ForestSchool>>
                ) {
                    if (response.isSuccessful) {

                        for (item in response.body()!!) {
                            forestSchoolList.apply {
                                add(
                                    LocationData(
                                        type = "",
                                        name = item.name,
                                        address = item.address,
                                        trees = ""
                                    )
                                )
                            }
                            forestSchoolDummy.apply {
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
                        locationExperienceAdapter.datas = forestSchoolList
                        locationExperienceAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<ArrayList<ForestSchool>>, t: Throwable) {
                    Log.e("fail", t.message.toString())
                }
            })

    }

    fun getGongBangData() {
        RequestToServer.service.requestGongbang()
            .enqueue(object : Callback<ArrayList<GongBangResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<GongBangResponse>>,
                    response: Response<ArrayList<GongBangResponse>>
                ) {
                    if (response.isSuccessful) {

                        for (item in response.body()!!) {
                            gongBangList.apply {
                                add(
                                    LocationData(
                                        type = "",
                                        name = item.name,
                                        address = item.address,
                                        trees = ""
                                    )
                                )
                            }

                            gongbangData.apply {
                                add(
                                    GongBangResponse(
                                        name = item.name,
                                        description = item.description,
                                        address = item.address,
                                        hours = item.hours,
                                        runtime = item.runtime,
                                        participants = item.participants,
                                        fee = item.fee,
                                        fee_int = item.fee_int,
                                        img_list = item.img_list
                                    )
                                )
                            }

                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<GongBangResponse>>, t: Throwable) {
                    Log.e("fail", t.message.toString())
                }

            })


    }

}