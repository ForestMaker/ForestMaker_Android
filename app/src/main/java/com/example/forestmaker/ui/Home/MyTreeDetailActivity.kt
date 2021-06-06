package com.example.forestmaker.ui.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.forestmaker.R
import kotlinx.android.synthetic.main.activity_my_tree_detail.*

class MyTreeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tree_detail)


        // 테두리 자르기 위해 background 테두리에 맞추기
        act_mytree_detail_img.clipToOutline = true

        Glide.with(this).load(intent.getStringExtra("img")).apply(
            RequestOptions().transforms(
                CenterCrop(),
                RoundedCorners(13)
            )).into(act_mytree_detail_img)

        act_mytree_detail_txt_name.text = intent.getStringExtra("name")
        act_mytree_detail_txt_date.text = intent.getStringExtra("date")
        act_mytree_detail_txt_location.text = intent.getStringExtra("location")


    }
}