package com.example.forestmaker.ui.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forestmaker.R
import com.example.forestmaker.data.MyTreeData
import com.example.forestmaker.server.RequestToServer
import kotlinx.android.synthetic.main.activity_my_tree.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTreeActivity : AppCompatActivity() {

    var id = ""
    val mytreeData = mutableListOf<MyTreeData>()
    lateinit var myTreeAdapter: MyTreeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tree)

        id = intent.getStringExtra("id").toString()

        // 서버 연결
//        getData(id)

        act_mytree_btn_back.setOnClickListener {
            finish()
        }

        myTreeAdapter = MyTreeAdapter(this,
            object : MytreeViewHolder.onClickListener{
                override fun onClickItem(position: Int) {
                    val intent = Intent(this@MyTreeActivity, MyTreeDetailActivity::class.java)
                    intent.putExtra("img", mytreeData[position].mytreeImg)
                    intent.putExtra("name", mytreeData[position].mytreeNamge)
                    intent.putExtra("date", mytreeData[position].mytreeDate)
                    intent.putExtra("location", mytreeData[position].mytreeLocation)
                    startActivity(intent)
                }

            })

        act_mytree_recyclerview.adapter = myTreeAdapter
        act_mytree_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        loadData()

    }

    fun loadData() {
        mytreeData.apply {
            add(
                MyTreeData(
                    mytreeImg = "https://www.dailimseed.co.kr/modules/shop/files/2015/09/23/1019_1.jpg?v=20210317094229",
                    mytreeNamge = "목백일홍",
                    mytreeDate = "2021.02.01",
                    mytreeLocation = "어느어느 식물원"
                )
            )
            add(
                MyTreeData(
                    mytreeImg = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRvybiJYJvof0WXnA0WqDYkNVatZU4uAXtlaw&usqp=CAU",
                    mytreeNamge = "노각나무",
                    mytreeDate = "2018.08.13",
                    mytreeLocation = "어느어느 식물원"
                )
            )
            add(
                MyTreeData(
                    mytreeImg = "https://www.ulsanpress.net/news/photo/202005/351117_141164_1136.jpg",
                    mytreeNamge = "은행나무",
                    mytreeDate = "2020.07.01",
                    mytreeLocation = "어느어느 식물원"
                )
            )
            add(
                MyTreeData(
                    mytreeImg = "https://www.dailimseed.co.kr/modules/shop/files/2015/09/23/1019_1.jpg?v=20210317094229",
                    mytreeNamge = "목백일홍",
                    mytreeDate = "2021.02.01",
                    mytreeLocation = "어느어느 식물원"
                )
            )
            add(
                MyTreeData(
                    mytreeImg = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRvybiJYJvof0WXnA0WqDYkNVatZU4uAXtlaw&usqp=CAU",
                    mytreeNamge = "노각나무",
                    mytreeDate = "2018.08.13",
                    mytreeLocation = "어느어느 식물원"
                )
            )
            add(
                MyTreeData(
                    mytreeImg = "https://www.ulsanpress.net/news/photo/202005/351117_141164_1136.jpg",
                    mytreeNamge = "은행나무",
                    mytreeDate = "2020.07.01",
                    mytreeLocation = "어느어느 식물원"
                )
            )
            add(
                MyTreeData(
                    mytreeImg = "https://www.dailimseed.co.kr/modules/shop/files/2015/09/23/1019_1.jpg?v=20210317094229",
                    mytreeNamge = "목백일홍",
                    mytreeDate = "2021.02.01",
                    mytreeLocation = "어느어느 식물원"
                )
            )
            add(
                MyTreeData(
                    mytreeImg = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRvybiJYJvof0WXnA0WqDYkNVatZU4uAXtlaw&usqp=CAU",
                    mytreeNamge = "노각나무",
                    mytreeDate = "2018.08.13",
                    mytreeLocation = "어느어느 식물원"
                )
            )
            add(
                MyTreeData(
                    mytreeImg = "https://www.ulsanpress.net/news/photo/202005/351117_141164_1136.jpg",
                    mytreeNamge = "은행나무",
                    mytreeDate = "2020.07.01",
                    mytreeLocation = "어느어느 식물원"
                )
            )
        }

        myTreeAdapter.datas = mytreeData
        myTreeAdapter.notifyDataSetChanged()
    }

//    private fun getData(id: String) {
//        RequestToServer.service.requestMyTree(id).enqueue(object : Callback<MutableList<MyTreeData>>{
//            override fun onResponse(
//                call: Call<MutableList<MyTreeData>>,
//                response: Response<MutableList<MyTreeData>>
//            ) {
//                if (response.isSuccessful) {
//                    myTreeAdapter.datas = response.body().toString()
//                    myTreeAdapter.datas = it
//
//                    for(i in 0 until (response.body()?.size ?: 1)){
//                        myTreeAdapter.datas.add(
//                            MyTreeData(
//                                mytreeImg = response.body(),
//                                mytreeNamge: String,
//                                mytreeDate: String,
//                                mytreeLocation: String
//                            )
//                        )
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<MutableList<MyTreeData>>, t: Throwable) {
//                Log.e("error", t.message.toString())
//            }
//
//        })
//    }
}