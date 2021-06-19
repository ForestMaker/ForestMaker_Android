package com.example.forestmaker.ui.Reserve.Experience

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.forestmaker.R
import com.example.forestmaker.data.BannerData
import kotlinx.android.synthetic.main.activity_experience.*

class ExperienceActivity : AppCompatActivity() {

    var forestSchoolDatas = mutableListOf<BannerData>()
    var recycleData = mutableListOf<BannerData>()



    lateinit var recycleAdapter: RecycleAdapter
    lateinit var forestSchoolAdapter: ForestSchoolAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experience)

        act_experience_btn_back.setOnClickListener {
            finish()
        }

        act_experience_layout_forestSchool.setOnClickListener {
            val intent = Intent(this, ExperienceOptionActivity::class.java)
            startActivity(intent)
        }

        recycleAdapter = RecycleAdapter(
            this,
            object : RecycleViewHolder.OnClickListener {
                override fun clickItem(position: Int) {

                }

            }
        )

        forestSchoolAdapter = ForestSchoolAdapter(
            this,
            object : ForestSchoolViewHolder.OnClickListener{
                override fun clickItem(position: Int) {

                }

            }
        )

        act_experience_forestSchool_recyclerview.adapter = forestSchoolAdapter
        act_experience_forestSchool_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(act_experience_forestSchool_recyclerview)

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

        forestSchoolDatas.apply {
            add(
                BannerData(
                    "https://i.pinimg.com/564x/fd/ce/07/fdce074ecbbde2001aa341b2d8cc40fe.jpg",
                    ""
                )
            )

            add(
                BannerData(
                    "https://i.pinimg.com/564x/14/15/38/1415387cba0c6fa8b62281ed62ea4b61.jpg",
                    ""
                )
            )

            add(
                BannerData(
                    "https://i.pinimg.com/564x/5f/50/03/5f5003b87d39a576a11e9f17d6547bff.jpg",
                    ""
                )
            )
        }

        forestSchoolAdapter.datas = forestSchoolDatas
        forestSchoolAdapter.notifyDataSetChanged()
    }
}