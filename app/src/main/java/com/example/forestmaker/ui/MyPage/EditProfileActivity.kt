package com.example.forestmaker.ui.MyPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.forestmaker.R
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        act_edit_profile_btn_back.setOnClickListener {
            finish()
        }


    }
}