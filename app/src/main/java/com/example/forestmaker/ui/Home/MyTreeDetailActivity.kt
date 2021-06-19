package com.example.forestmaker.ui.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.forestmaker.R
import kotlinx.android.synthetic.main.activity_my_tree_detail.*
import kotlinx.android.synthetic.main.frame_act_mytree_detail_normal.*
import kotlinx.android.synthetic.main.frame_mytree_detail_edit.*

class MyTreeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tree_detail)

        act_mytree_detail_btn_back.setOnClickListener {
            finish()
        }

        Glide.with(this).load(intent.getStringExtra("img")).apply(
            RequestOptions().transforms(
                CenterCrop(),
                RoundedCorners(30)
            )
        ).into(act_mytree_detail_img)

        act_mytree_detail_txt_name.text = intent.getStringExtra("name")
        act_mytree_detail_txt_date.text = intent.getStringExtra("date")
        act_mytree_detail_txt_location.text = intent.getStringExtra("location")

        act_mytree_detail_btn_option.setOnClickListener {
            var pop = PopupMenu(this, act_mytree_detail_btn_option)

            menuInflater.inflate(R.menu.popup, pop.menu)

            pop.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_menu1 ->
                        changeView(1)
                }
                false
            }
            pop.show()
        }

        act_mytree_detail_btn_edit_done.setOnClickListener {
            act_mytree_detail_txt_contents.text = act_mytree_detail_edit.text.toString()
            changeView(0)
        }

    }

    private fun changeView(index: Int) {

        val frameNormal = findViewById<ConstraintLayout>(R.id.frame_normal)
        val frameEdit = findViewById<ConstraintLayout>(R.id.frame_edit)

        when (index) {
            // normal view
            0 -> {
                frameEdit.visibility = View.INVISIBLE
                frameNormal.visibility = View.VISIBLE
            }
            //edit view
            1 -> {
                frameNormal.visibility = View.INVISIBLE
                frameEdit.visibility = View.VISIBLE
            }
        }
    }
}