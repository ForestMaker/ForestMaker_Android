package com.forest.forestmaker.ui.reserve.Experience

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.forest.forestmaker.R
import com.forest.forestmaker.data.ShoppingCartData
import kotlinx.android.synthetic.main.activity_experience_option.*

class ExperienceOptionActivity : AppCompatActivity() {

    lateinit var experienceOptionAdapter: ExperienceOptionAdapter
    var data = ArrayList<ShoppingCartData>()
    var userEmail = ""
    var type = ""
    var name = ""
    var address = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experience_option)

        setIntentData()
        setButton()
        setAdapter()
        getExperienceOptionData()
    }

    private fun setIntentData() {
        userEmail = intent.getStringExtra("user_email").toString()
        data = intent.getParcelableArrayListExtra<ShoppingCartData>("shoppingCartList")!!
        type = intent.getStringExtra("type").toString()
        name = intent.getStringExtra("name").toString()
        address = intent.getStringExtra("address").toString()
    }

    private fun setButton() {
        act_experience_option_btn_back.setOnClickListener {
            finish()
        }

        act_experience_option_btn_next.setOnClickListener {
            val intent = Intent(this, SelectExperienceDateActivity::class.java)

            intent.putExtra("type", type)
            intent.putExtra("address", address)
            intent.putExtra("name", name)
            intent.putExtra("shoppingCartList", data)
            intent.putExtra("user_email", userEmail)

            startActivity(intent)
            return@setOnClickListener
        }
    }

    private fun setAdapter() {
        experienceOptionAdapter = ExperienceOptionAdapter(this)
        act_experience_option_recyclerview.adapter = experienceOptionAdapter
        act_experience_option_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun getExperienceOptionData() {
        experienceOptionAdapter.datas.apply {
            add("숲 전문가와 함께하는 둘레길 숲 해설")
            add("피톤치드 뿜뿜 나무 공부하기")
            add("엄마와 아이랑 같이 하는 명상 체험")
            add("우리 숲에 살고 있는 동·식물 소개")
            add("스트레스 날릴 수 있는 힐링 체험")
            add("맨발로 흙 걷기 체험")
        }

        experienceOptionAdapter.notifyDataSetChanged()

    }
}