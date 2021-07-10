package com.example.forestmaker.ui.reserve.Planting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.SelectTreeData
import com.example.forestmaker.server.RequestToServer
import com.example.forestmaker.server.data.StoreItem
import kotlinx.android.synthetic.main.activity_select_tree.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectTreeActivity : AppCompatActivity() {

    lateinit var selectTreeAdapter: SelectTreeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_tree)

        act_select_tree_btn_back.setOnClickListener { finish() }
        act_select_tree_btn_next.setOnClickListener {
            val intent = Intent(this, ArboretumActivity::class.java)
            startActivity(intent)
            finish()
        }

        selectTreeAdapter = SelectTreeAdapter(this,
        object : SelectTreeViewHolder.OnClickListener{
            override fun onClickTree(position: Int) {
                //
            }

        })

        act_select_tree_recyclerview.adapter = selectTreeAdapter
        act_select_tree_recyclerview.layoutManager = GridLayoutManager(this, 3)

        getTreeData()
    }

    fun getTreeData() {
        RequestToServer.service.requestStoreTonic().enqueue(object :Callback<ArrayList<StoreItem>> {
            override fun onResponse(
                call: Call<ArrayList<StoreItem>>,
                response: Response<ArrayList<StoreItem>>
            ) {
                if (response.isSuccessful) {
                    val list = ArrayList<SelectTreeData>()

                    for (item in response.body()!!) {
                        list.apply {
                            add(
                                SelectTreeData(
                                    image = item.photo,
                                    name = item.name,
                                    price = item.price
                                )
                            )
                        }
                    }

                    selectTreeAdapter.data = list
                    selectTreeAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ArrayList<StoreItem>>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }

        })
    }
}