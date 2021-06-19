package com.example.forestmaker.ui.Reserve.Experience

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forestmaker.R
import kotlinx.android.synthetic.main.activity_experience_option.*

class ExperienceOptionActivity : AppCompatActivity() {

    lateinit var experienceOptionAdapter: ExperienceOptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experience_option)

        act_experience_option_btn_next.setOnClickListener {
            finish()
        }

        experienceOptionAdapter = ExperienceOptionAdapter(this)
        act_experience_option_recyclerview.adapter = experienceOptionAdapter
        act_experience_option_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        loadData()
    }

    fun loadData() {
        experienceOptionAdapter.datas.apply {
            add("숲에 살고 있는 동·식물 소개")
            add("스트레스 날릴 수 있는 힐링 체험")
            add("맨발로 흙 걷기 체험")
            add("전문가와 함께하는 둘레길 숲 해설")
            add("피톤치드 뿜뿜 나무 공부하기")
            add("부모와 아이가 같이 하는 명상 체험")
        }

        experienceOptionAdapter.notifyDataSetChanged()

    }
}