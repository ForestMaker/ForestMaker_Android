package com.example.forestmaker.ui.home

import android.content.Intent
import android.content.LocusId
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.MyTreeData
import com.example.forestmaker.data.StoreData
import com.example.forestmaker.server.RequestToServer
import com.example.forestmaker.server.data.MyTree
import com.example.forestmaker.server.data.MyTreeListResponse
import kotlinx.android.synthetic.main.activity_my_tree.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTreeActivity : AppCompatActivity() {

    var user = ""
    var mytreeData = mutableListOf<MyTreeData>()
    lateinit var myTreeAdapter: MyTreeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tree)

        user = intent.getStringExtra("user").toString()

        act_mytree_btn_back.setOnClickListener {
            finish()
        }

        myTreeAdapter = MyTreeAdapter(this,
            object : MytreeViewHolder.onClickListener{
                override fun onClickItem(position: Int) {
                    val intent = Intent(this@MyTreeActivity, MyTreeDetailActivity::class.java)
                    intent.putExtra("_id", mytreeData[position]._id)
                    startActivity(intent)
                }

            })

        act_mytree_recyclerview.adapter = myTreeAdapter
        act_mytree_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


//        loadData()
        getMyTreeData()
    }

    fun getMyTreeData() {
        RequestToServer.service.requestMyTree(user).enqueue(object :Callback<MyTreeListResponse>{
            override fun onResponse(
                call: Call<MyTreeListResponse>,
                response: Response<MyTreeListResponse>
            ) {
                if (response.isSuccessful) {
                    val list = mutableListOf<MyTreeData>()

                    for (item in response.body()!!.data) {
                        list.apply {
                            add(
                                MyTreeData(
                                    _id = item._id,
                                    mytreeImg = item.tree_img,
                                    mytreeName = item.tree_name,
                                    mytreeDate = item.tree_date,
                                    mytreeLocation= item.tree_location
                                )
                            )
                        }
                    }

                    mytreeData = list
                    myTreeAdapter.datas = mytreeData
                    myTreeAdapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<MyTreeListResponse>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }

        })
    }
//
//    fun loadData() {
//        mytreeData.apply {
//            add(
//                MyTreeData(
//                    mytreeImg = "https://postfiles.pstatic.net/MjAyMTA2MjlfNDcg/MDAxNjI0OTAyMDM0Mjgx.OcOqukoInqMU_wCMJOPuSVINwYl8CuKX0F838cKS7Wcg.7raytiarBF-DY1CC9-YBSWQlQWPrqx_vp76t8TtVkQMg.PNG.ghyun0914/%EB%82%99%EC%97%BD%EC%86%A1.png?type=w773",
//                    mytreeNamge = "낙엽송",
//                    mytreeDate = "2021.02.01",
//                    mytreeLocation = "어느어느 식물원"
//                )
//            )
//            add(
//                MyTreeData(
//                    mytreeImg = "https://postfiles.pstatic.net/MjAyMTA2MjlfMjYy/MDAxNjI0OTAyMDM0Mjg4.rTSEAreUBFx7o2soVMfNIowLsZ8YHdj6bnCdkNjbCx0g.1yDBMbBOVdpQhIJD52kWtA_HI-zqSL7d59kV1bNV6Gkg.PNG.ghyun0914/%EA%B5%B4%EC%B0%B8%EB%82%98%EB%AC%B4.png?type=w773",
//                    mytreeNamge = "굴참나",
//                    mytreeDate = "2018.08.13",
//                    mytreeLocation = "어느어느 식물원"
//                )
//            )
//            add(
//                MyTreeData(
//                    mytreeImg = "https://postfiles.pstatic.net/MjAyMTA2MjlfMTIw/MDAxNjI0OTAyMDM0Mjkw.ubP7FqA4AJVRRbprfdlIRlrknjYfm7dYqyoF7pz90Jwg.ma34ScodOUiOUJcXjPaoDUx-3i0BmKqkAbRdxT7XWrgg.PNG.ghyun0914/%EC%86%8C%EB%82%98%EB%AC%B4.png?type=w773",
//                    mytreeNamge = "굴참나무",
//                    mytreeDate = "2020.07.01",
//                    mytreeLocation = "어느어느 식물원"
//                )
//            )
//        }
//
//        myTreeAdapter.datas = mytreeData
//        myTreeAdapter.notifyDataSetChanged()
//    }
}