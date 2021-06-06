package com.example.forestmaker.ui.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forestmaker.R
import com.example.forestmaker.data.MyTreeData
import kotlinx.android.synthetic.main.activity_my_tree.*

class MyTreeActivity : AppCompatActivity() {

    val mytreeData = mutableListOf<MyTreeData>()
    lateinit var myTreeAdapter: MyTreeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tree)

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
}