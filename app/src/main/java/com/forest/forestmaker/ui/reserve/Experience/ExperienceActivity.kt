package com.forest.forestmaker.ui.reserve.Experience

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.forest.forestmaker.R
import com.forest.forestmaker.data.BannerData
import com.forest.forestmaker.data.ShoppingCartData
import com.forest.forestmaker.server.RequestToServer
import com.forest.forestmaker.server.data.ForestSchool
import com.forest.forestmaker.server.data.GongBangResponse
import com.forest.forestmaker.ui.reserve.Experience.gongbang.GongBangActivity
import com.forest.forestmaker.ui.reserve.ShoppingCartAdapter
import com.forest.forestmaker.ui.reserve.ShoppingCartViewHolder
import kotlinx.android.synthetic.main.activity_experience.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExperienceActivity : AppCompatActivity() {

    var recycleData = mutableListOf<BannerData>()
    var forestSchoolDummy = ArrayList<ForestSchool>()
    var gongbangData = ArrayList<GongBangResponse>()

    lateinit var shoppingCartAdapter: ShoppingCartAdapter
    lateinit var recycleAdapter: RecycleAdapter
    var userEmail = ""
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experience)

        setIntentData()
        setForestSchool()
        setButton()
        setAdapter()

        getGongBangData()

    }

    private fun setIntentData() {
        userEmail = intent.getStringExtra("user_email").toString()
        forestSchoolDummy = intent.getParcelableArrayListExtra<ForestSchool>("forestschool")!!
        position = intent.getIntExtra("position", 0)
    }

    private fun setButton() {
        act_experience_btn_back.setOnClickListener {
            finish()
        }

        act_experience_btn_next.setOnClickListener {
            shoppingCartAdapter.datas.add(
                ShoppingCartData(
                    itemImg = forestSchoolDummy[position].image,
                    itemName = forestSchoolDummy[position].name,
                    itemNumber = 1,
                    itemPrice_int = forestSchoolDummy[position].fee_int,
                    itemPrice_str = forestSchoolDummy[position].fee
                )
            )
            shoppingCartAdapter.notifyDataSetChanged()

            val intent = Intent(this, ExperienceOptionActivity::class.java)
            intent.putExtra("type", "체험")
            intent.putExtra("address", forestSchoolDummy[position].address)
            intent.putExtra("name", forestSchoolDummy[position].name)
            intent.putExtra("shoppingCartList", shoppingCartAdapter.datas)
            intent.putExtra("user_email", userEmail)
            startActivity(intent)
            return@setOnClickListener
        }
    }

    private fun setAdapter() {
        shoppingCartAdapter =
            ShoppingCartAdapter(this, object : ShoppingCartViewHolder.onClickListener {
                override fun onClickItemDelete(position: Int) {}
                override fun onPlusItem(position: Int) {}
                override fun onMinusItem(position: Int) {}
            })

        recycleAdapter = RecycleAdapter(
            this,
            object : RecycleViewHolder.OnClickListener {
                override fun clickItem(position: Int) {

                    val intent = Intent(this@ExperienceActivity, GongBangActivity::class.java)
                    intent.putExtra("gongbangList", gongbangData)
                    intent.putExtra("position", position)
                    intent.putExtra("user_email", userEmail)
                    startActivity(intent)
                }
            }
        )

        act_experience_recycler_recyclerview.adapter = recycleAdapter
        act_experience_recycler_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

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
                            recycleData.apply {
                                add(
                                    BannerData(
                                        bannerImg = item.img_list[0]
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

                        recycleAdapter.datas = recycleData
                        recycleAdapter.notifyDataSetChanged()
                    }
                }

                override fun onFailure(call: Call<ArrayList<GongBangResponse>>, t: Throwable) {
                    Log.e("fail", t.message.toString())
                }

            })


    }

    private fun setForestSchool() {
        act_experience_txt_forestSchool_name.text = forestSchoolDummy[position].name
        act_experience_txt_forestSchool_location.text = forestSchoolDummy[position].address
        Glide.with(this).load(forestSchoolDummy[position].image).apply(
            RequestOptions().transforms(
                CenterCrop(),
                RoundedCorners(30)
            )
        ).into(act_experience_forestSchool_image)
        act_experience_hours.text = forestSchoolDummy[position].hours
        act_experience_runtime.text = forestSchoolDummy[position].runtime
        act_experience_participants.text = forestSchoolDummy[position].participants
        act_experience_fee.text = forestSchoolDummy[position].fee
        act_experience_info.text = forestSchoolDummy[position].info
    }
}