package com.example.forestmaker.ui.reserve.Experience

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.forestmaker.R
import com.example.forestmaker.data.BannerData
import com.example.forestmaker.data.ShoppingCartData
import com.example.forestmaker.server.data.ForestSchool
import kotlinx.android.synthetic.main.activity_experience.*

class ExperienceActivity : AppCompatActivity() {

    var recycleData = mutableListOf<BannerData>()
    var forestschoolDummy = ArrayList<ForestSchool>()
    var position = 0
    lateinit var recycleAdapter: RecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experience)

        forestschoolDummy = intent.getParcelableArrayListExtra<ForestSchool>("forestschool")!!
        position = intent.getIntExtra("position", 0)
        setForestSchool()

        act_experience_btn_back.setOnClickListener {
            finish()
        }

        act_experience_layout_forestSchool.setOnClickListener {
            val intent = Intent(this, ExperienceOptionActivity::class.java)
            startActivity(intent)
            finish()
        }

        recycleAdapter = RecycleAdapter(
            this,
            object : RecycleViewHolder.OnClickListener {
                override fun clickItem(position: Int) {

                }

            }
        )

        act_experience_recycler_recyclerview.adapter = recycleAdapter
        act_experience_recycler_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        loadData()

    }

    fun loadData(){
        recycleData.apply {
            add(
                BannerData(
                    "https://i.pinimg.com/564x/fd/ce/07/fdce074ecbbde2001aa341b2d8cc40fe.jpg",
                    "뚝딱뚝딱 나무로\n" +
                            "주방용품 만들기 프로그램"
                )
            )

            add(
                BannerData(
                    "https://i.pinimg.com/564x/14/15/38/1415387cba0c6fa8b62281ed62ea4b61.jpg",
                    "썩은 나무로\n" +
                            "의자 만들기 프로그램"
                )
            )

            add(
                BannerData(
                    "https://i.pinimg.com/564x/5f/50/03/5f5003b87d39a576a11e9f17d6547bff.jpg",
                    "친환경 대나무 칫솔"
                )
            )
        }

        recycleAdapter.datas = recycleData
        recycleAdapter.notifyDataSetChanged()

    }

    fun setForestSchool() {
        act_experience_txt_forestSchool_name.text = forestschoolDummy[position].name
        act_experience_txt_forestSchool_location.text = forestschoolDummy[position].address
        Glide.with(this).load(forestschoolDummy[position].image).apply(
            RequestOptions().transforms(
                CenterCrop(),
                RoundedCorners(30)
            )).into(act_experience_forestSchool_image)
        act_experience_hours.text = forestschoolDummy[position].hours
        act_experience_runtime.text = forestschoolDummy[position].runtime
        act_experience_participants.text = forestschoolDummy[position].participants
        act_experience_fee.text = forestschoolDummy[position].fee
        act_experience_info.text = forestschoolDummy[position].info
    }
}