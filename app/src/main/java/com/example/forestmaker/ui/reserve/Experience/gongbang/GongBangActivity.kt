package com.example.forestmaker.ui.reserve.Experience.gongbang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.forestmaker.R
import com.example.forestmaker.server.data.GongBangResponse
import kotlinx.android.synthetic.main.activity_gong_bang.*

class GongBangActivity : AppCompatActivity() {

    lateinit var gongBangImgAdapter: GongBangImgAdapter
    var gongbangList = ArrayList<GongBangResponse>()
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gong_bang)

        gongbangList = intent.getParcelableArrayListExtra<GongBangResponse>("gongbangList")!!
        position = intent.getIntExtra("position", 0)

        gongBangImgAdapter = GongBangImgAdapter(this)
        act_gongbang_recyclerview.adapter = gongBangImgAdapter
        act_gongbang_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        setData()

    }

    fun setData() {
        act_gongbnag_name.text = gongbangList[position].name
        act_gongbang_location.text = gongbangList[position].address
        act_gongbang_info.text = gongbangList[position].description
        act_gongbang_fee.text = gongbangList[position].fee
        Glide.with(this).load(gongbangList[position].img_list[0]).apply(
            RequestOptions().transforms(
                CenterCrop(),
                RoundedCorners(30)
            )).into(act_gongbang_image)
        act_gongbang_hours.text = gongbangList[position].hours
        act_gongbang_runtime.text = gongbangList[position].runtime
        act_gongbang_participants.text = gongbangList[position].participants

        gongBangImgAdapter.datas = gongbangList[position].img_list
        gongBangImgAdapter.notifyDataSetChanged()
    }
}