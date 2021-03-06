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
            add("??? ???????????? ???????????? ????????? ??? ??????")
            add("???????????? ?????? ?????? ????????????")
            add("????????? ????????? ?????? ?????? ?????? ??????")
            add("?????? ?????? ?????? ?????? ??????????? ??????")
            add("???????????? ?????? ??? ?????? ?????? ??????")
            add("????????? ??? ?????? ??????")
        }

        experienceOptionAdapter.notifyDataSetChanged()

    }
}