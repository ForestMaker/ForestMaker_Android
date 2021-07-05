package com.example.forestmaker.ui.reserve.Experience

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

        act_experience_option_btn_back.setOnClickListener {
            finish()
        }

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
            add("숲속에서 내가 좋아하는 동물찾기")
            add("숲속에 사는 식물 관찰하기")
            add("꽃으로 왕관 만들기")

        }

        experienceOptionAdapter.notifyDataSetChanged()

    }
}