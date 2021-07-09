package com.example.forestmaker.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.forestmaker.R
import com.example.forestmaker.server.RequestToServer
import com.example.forestmaker.server.data.MyTreeListResponse
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_my_tree_detail.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.frame_act_mytree_detail_normal.*
import kotlinx.android.synthetic.main.frame_mytree_detail_edit.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTreeDetailActivity : AppCompatActivity() {

    var _id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tree_detail)

        _id = intent.getStringExtra("_id").toString()

        getMyTreeDetail()

        act_mytree_detail_btn_back.setOnClickListener {
            finish()
        }

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

            val jsonObject = JSONObject()
            jsonObject.put("contents", act_mytree_detail_edit.text.toString())

            val contents = JsonParser.parseString(jsonObject.toString()) as JsonObject

            RequestToServer.service.requestEditMyTree(_id, contents).enqueue(object : Callback<MyTreeListResponse>{
                override fun onResponse(
                    call: Call<MyTreeListResponse>,
                    response: Response<MyTreeListResponse>
                ) {
                    if (response.isSuccessful){
                        Log.e("success to edit", response.body()?.data.toString())
                        act_mytree_detail_txt_contents.text = act_mytree_detail_edit.text.toString()
                        changeView(0)
                    }
                }

                override fun onFailure(call: Call<MyTreeListResponse>, t: Throwable) {
                    Log.e("fail to edit", t.message.toString())
                }

            })
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

    fun getMyTreeDetail() {
        RequestToServer.service.requestMyTreeDetail(_id)
            .enqueue(object : Callback<MyTreeListResponse> {
                override fun onResponse(
                    call: Call<MyTreeListResponse>,
                    response: Response<MyTreeListResponse>
                ) {
                    if (response.isSuccessful) {

                        Glide.with(this@MyTreeDetailActivity).load(response.body()?.data?.get(0)?.tree_img ?: "null"
                        ).apply(
                            RequestOptions().transforms(
                                CenterCrop(),
                                RoundedCorners(30)
                            )
                        ).into(act_mytree_detail_img)

                        act_mytree_detail_txt_name.text = response.body()?.data?.get(0)?.tree_name ?: "null"
                        act_mytree_detail_txt_location.text = response.body()?.data?.get(0)?.tree_location ?: "null"
                        act_mytree_detail_txt_date.text = response.body()?.data?.get(0)?.tree_date ?: "null"
                        act_mytree_detail_txt_contents.text = response.body()?.data?.get(0)?.tree_diary ?: "null"
                    }
                }

                override fun onFailure(call: Call<MyTreeListResponse>, t: Throwable) {
                    Log.e("fail", t.message.toString())
                }

            })
    }

    override fun onResume() {
        super.onResume()
        getMyTreeDetail()
    }
}