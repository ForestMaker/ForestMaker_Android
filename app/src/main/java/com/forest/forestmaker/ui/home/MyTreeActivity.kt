package com.forest.forestmaker.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.forest.forestmaker.R
import com.forest.forestmaker.data.MyTreeData
import com.forest.forestmaker.server.RequestToServer
import com.forest.forestmaker.server.data.MyTreeListResponse
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_my_tree.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTreeActivity : AppCompatActivity() {

    var userId = ""
    var myTreeData = mutableListOf<MyTreeData>()
    lateinit var myTreeAdapter: MyTreeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tree)

        setIntentData()
        setButton()
        setAdapter()
        getMyTreeData()
    }

    private fun setIntentData() {
        userId = intent.getStringExtra("user_email").toString()
    }

    private fun setButton() {
        act_mytree_btn_back.setOnClickListener {
            finish()
        }
    }

    private fun setAdapter() {
        myTreeAdapter = MyTreeAdapter(this,
            object : MytreeViewHolder.onClickListener {
                override fun onClickItem(position: Int) {
                    val intent = Intent(this@MyTreeActivity, MyTreeDetailActivity::class.java)
                    intent.putExtra("_id", myTreeData[position]._id)
                    intent.putExtra("user_email", userId)
                    startActivity(intent)
                    finish()
                }
            })

        act_mytree_recyclerview.adapter = myTreeAdapter
        act_mytree_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun getMyTreeData() {

        RequestToServer.service.requestMyTree(
            JsonParser.parseString(
                JSONObject().put(
                    "userid",
                    userId
                ).toString()
            ) as JsonObject
        ).enqueue(object : Callback<ArrayList<MyTreeListResponse>> {
            override fun onResponse(
                call: Call<ArrayList<MyTreeListResponse>>,
                response: Response<ArrayList<MyTreeListResponse>>
            ) {
                if (response.isSuccessful) {
                    val list = mutableListOf<MyTreeData>()

                    for (item in response.body()!!) {
                        list.apply {
                            add(
                                MyTreeData(
                                    _id = item._id,
                                    mytreeImg = item.photo,
                                    mytreeName = item.kind,
                                    mytreeDate = item.date,
                                    mytreeLocation = item.addr
                                )
                            )
                        }
                    }

                    myTreeData = list
                    myTreeAdapter.datas = myTreeData
                    myTreeAdapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<ArrayList<MyTreeListResponse>>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }

        })
    }
}