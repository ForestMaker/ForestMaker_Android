package com.example.forestmaker.ui.reserve.Experience

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.ForestSchoolData
import com.example.forestmaker.data.LocationData
import com.example.forestmaker.ui.reserve.LocationAdapter
import com.example.forestmaker.ui.reserve.LocationViewHolder
import kotlinx.android.synthetic.main.activity_select_experience.*

class SelectExperienceActivity : AppCompatActivity() {

    var dummyData = ArrayList<ForestSchoolData>()
    lateinit var locationExperienceAdapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_experience)

        act_select_experience_location_btn_back.setOnClickListener {
            finish()
        }

        locationExperienceAdapter = LocationAdapter(this, object : LocationViewHolder.onClickListener{
            override fun onClickItem(position: Int) {

                val intent = Intent(this@SelectExperienceActivity, ExperienceActivity::class.java)
                startActivity(intent)
                finish()
            }

        })

        act_select_experience_location_recyclerview.adapter = locationExperienceAdapter
        act_select_experience_location_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        loadData()
//        setDummyData()

    }

    private fun loadData() {
        locationExperienceAdapter.datas.apply {
            add(
                LocationData("충청도 신북시", "유포산52-11")
            )

            add(
                LocationData("강원도 평창시", "대관령 14-214")
            )

            add(
                LocationData("전라남도 담양시","추월산로 593")
            )

            add(
                LocationData("광주광역시 남구", "남 사 177")
            )

            add(
                LocationData("강원도 원주시 ","백운산길 95")
            )

            add(
                LocationData("대구광역시 달서구 ", "공원순환로 12")
            )
        }
        locationExperienceAdapter.notifyDataSetChanged()
    }

//    private fun setDummyData() {
//        dummyData.apply {
//            add(
//                ForestSchoolData(
//                    "우리 숲에 살고 있는 동물과 식물 소개",
//                    "춘천 신북 유포 산52-11",
//                    "https://cdn.pixabay.com/photo/2016/06/27/15/28/roe-deer-1482712_1280.jpg",
//                    "본 프로그램은 7세 부터 13세까지 참여 할 수 있는 프로그램입니다."
//                )
//            )
//            add(
//                ForestSchoolData(
//                    "스트레스 날릴 수 있는 힐링체험",
//                    "강원 평창 대관령 14-214",
//                    "https://cdn.pixabay.com/photo/2017/06/08/17/15/tongyeong-2384216_1280.jpg",
//                    "본 프로그램은 모든 연령이 참여 할 수 있는 프로그램입니다."
//                )
//            )
//            add(
//                ForestSchoolData(
//                    "엄마와 아이랑 같이 하는 명상 채험",
//                    "전남 담양 용 추월산로 593",
//                    "https://cdn.pixabay.com/photo/2017/11/21/09/37/park-2967710_1280.jpg",
//                    "본 프로그램은 10세 이하 어린이와 아이의 어머님이 참여할 수 있는 프로그램입니다."
//                )
//            )
//            add(
//                ForestSchoolData(
//                    "맨발로 흙 걷기 체험",
//                    "광주 남구 사 177",
//                    "https://cdn.pixabay.com/photo/2017/05/29/11/16/footprint-2353510_1280.jpg",
//                    "본 프로그램은 모든 연령이 참여 할 수 있는 프로그램입니다."
//                )
//            )
//            add(
//                ForestSchoolData(
//                    "숲 전문가와 함께하는 둘레길 숲 해설",
//                    "강원 원주 판부 백운산길 95",
//                    "https://cdn.pixabay.com/photo/2020/03/18/23/58/chunnam-4945781_1280.jpg",
//                    "본 프로그램은 6세 이상 8세 이하 어린이까지 참여할 수 있는 프로그램입니다."
//                )
//            )
//            add(
//                ForestSchoolData(
//                    "피톤치드 뿜뿜 나무 공부하기",
//                    "대구광역시 달서구 공원순환로 12",
//                    "https://cdn.pixabay.com/photo/2013/02/21/19/12/lumber-84678_1280.jpg",
//                    "본 프로그램은 전 연령이 참여할 수 있는 프로그램입니다."
//                )
//            )
//        }
//    }
}