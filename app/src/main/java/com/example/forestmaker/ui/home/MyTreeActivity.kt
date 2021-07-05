package com.example.forestmaker.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.MyTreeData
import kotlinx.android.synthetic.main.activity_my_tree.*

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
                    mytreeImg = "https://postfiles.pstatic.net/MjAyMTA2MjlfNDcg/MDAxNjI0OTAyMDM0Mjgx.OcOqukoInqMU_wCMJOPuSVINwYl8CuKX0F838cKS7Wcg.7raytiarBF-DY1CC9-YBSWQlQWPrqx_vp76t8TtVkQMg.PNG.ghyun0914/%EB%82%99%EC%97%BD%EC%86%A1.png?type=w773",
                    mytreeNamge = "낙엽송",
                    mytreeDate = "2021.02.01",
                    mytreeLocation = "어느어느 식물원"
                )
            )
            add(
                MyTreeData(
                    mytreeImg = "https://postfiles.pstatic.net/MjAyMTA2MjlfMjYy/MDAxNjI0OTAyMDM0Mjg4.rTSEAreUBFx7o2soVMfNIowLsZ8YHdj6bnCdkNjbCx0g.1yDBMbBOVdpQhIJD52kWtA_HI-zqSL7d59kV1bNV6Gkg.PNG.ghyun0914/%EA%B5%B4%EC%B0%B8%EB%82%98%EB%AC%B4.png?type=w773",
                    mytreeNamge = "굴참나",
                    mytreeDate = "2018.08.13",
                    mytreeLocation = "어느어느 식물원"
                )
            )
            add(
                MyTreeData(
                    mytreeImg = "https://postfiles.pstatic.net/MjAyMTA2MjlfMTIw/MDAxNjI0OTAyMDM0Mjkw.ubP7FqA4AJVRRbprfdlIRlrknjYfm7dYqyoF7pz90Jwg.ma34ScodOUiOUJcXjPaoDUx-3i0BmKqkAbRdxT7XWrgg.PNG.ghyun0914/%EC%86%8C%EB%82%98%EB%AC%B4.png?type=w773",
                    mytreeNamge = "굴참나무",
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