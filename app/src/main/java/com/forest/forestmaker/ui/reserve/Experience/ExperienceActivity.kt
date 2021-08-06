package com.forest.forestmaker.ui.reserve.Experience

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.forest.forestmaker.R
import com.forest.forestmaker.data.BannerData
import com.forest.forestmaker.data.ShoppingCartData
import com.forest.forestmaker.server.data.ForestSchool
import com.forest.forestmaker.ui.reserve.ShoppingCartAdapter
import kotlinx.android.synthetic.main.activity_experience.*

class ExperienceActivity : AppCompatActivity() {

    var recycleData = mutableListOf<BannerData>()
    var forestSchoolDummy = ArrayList<ForestSchool>()

    lateinit var shoppingCartAdapter: ShoppingCartAdapter
    var userEmail = ""
    var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_experience)

        setIntentData()
        setForestSchool()
        setButton()

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