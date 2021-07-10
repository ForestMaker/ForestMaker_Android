package com.example.forestmaker.ui.reserve.Store

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.ShoppingCartData
import com.example.forestmaker.data.StoreData
import com.example.forestmaker.server.RequestToServer
import com.example.forestmaker.server.data.StoreResponse
import com.example.forestmaker.ui.reserve.*
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.hhl.gridpagersnaphelper.GridPagerSnapHelper
import com.hhl.recyclerviewindicator.LinePageIndicator
import kotlinx.android.synthetic.main.activity_store.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StoreActivity : AppCompatActivity() {

    var shoppingCartData = ArrayList<ShoppingCartData>()
    lateinit var shoppingCartAdapter: ShoppingCartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        val type = JSONObject().put("type", "tree_data")

        val month = intent.getStringExtra("month")
        val day = intent.getStringExtra("day")
        val hour = intent.getStringExtra("hour")
        val minute = intent.getStringExtra("minute")
        val peopleNumber = intent.getStringExtra("peopleNumber")
//        intent.getStringExtra("month")?.let { Log.d("month", it) }
//        intent.getStringExtra("day")?.let { Log.d("day", it) }
//        intent.getStringExtra("hour")?.let { Log.d("hour", it) }
//        intent.getStringExtra("minute")?.let { Log.d("minute", it) }
//        intent.getStringExtra("peopleNumber")?.let { Log.d("peopleNumber", it) }

        act_store_text_reserveTime.text = month + "월" + day+ "일" + hour+":"+minute
        act_store_text_reserveAddress.text = "마곡나루역 근처"

        // filter initialize
        act_store_filter_btn_all.isSelected = true
        act_store_filter_btn_tree.isSelected = false
        act_store_filter_btn_supplements.isSelected = false
        act_store_filter_btn_rent.isSelected = false

        act_store_btn_back.setOnClickListener {
            finish()
        }

        act_store_btn_buy.setOnClickListener {
            val intent = Intent(this, ShoppingCartActivity::class.java)
            intent.putExtra("shoppingCartList", shoppingCartAdapter.datas)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        shoppingCartAdapter = ShoppingCartAdapter(
            this,
            object : ShoppingCartViewHolder.onClickListener {
                override fun onClickItemDelete(position: Int) {

                    shoppingCartAdapter.datas.removeAt(position)
                    shoppingCartAdapter.notifyDataSetChanged()
                }

                override fun onPlusItem(position: Int) {
                    //
                }

                override fun onMinusItem(position: Int) {
                    //
                }

            }
        )

//        shoppingCartData.clear()
        getStoreItem("all")

        act_store_filter_btn_all.setOnClickListener {
            act_store_filter_btn_all.isSelected = true
            act_store_filter_btn_tree.isSelected = false
            act_store_filter_btn_supplements.isSelected = false
            act_store_filter_btn_rent.isSelected = false

            getStoreItem("all")

        }
        act_store_filter_btn_tree.setOnClickListener {
            act_store_filter_btn_all.isSelected = false
            act_store_filter_btn_tree.isSelected = true
            act_store_filter_btn_supplements.isSelected = false
            act_store_filter_btn_rent.isSelected = false

            getStoreItem("tree_data")
        }
        act_store_filter_btn_supplements.setOnClickListener {
            act_store_filter_btn_all.isSelected = false
            act_store_filter_btn_tree.isSelected = false
            act_store_filter_btn_supplements.isSelected = true
            act_store_filter_btn_rent.isSelected = false

            getStoreItem("tonic_data")
        }
        act_store_filter_btn_rent.setOnClickListener {
            act_store_filter_btn_all.isSelected = false
            act_store_filter_btn_tree.isSelected = false
            act_store_filter_btn_supplements.isSelected = false
            act_store_filter_btn_rent.isSelected = true

            getStoreItem("rental_data")
        }

    }

    fun configRecyclerView(row: Int, column: Int, data: MutableList<StoreData>) {
//        val secondRV = findViewById<View>(R.id.act_store_item_recyclerview) as RecyclerView
//        secondRV.setHasFixedSize(true)

        //setLayoutManager
        val gridLayoutManager = GridLayoutManager(this, row, LinearLayoutManager.HORIZONTAL, false)
        act_store_item_recyclerview.layoutManager = gridLayoutManager

        //attachToRecyclerView
        val gridPagerSnapHelper = GridPagerSnapHelper()
        gridPagerSnapHelper.setRow(row).setColumn(column)
        act_store_item_recyclerview.onFlingListener = null
        gridPagerSnapHelper.attachToRecyclerView(act_store_item_recyclerview)
        val screenWidth: Int = Resources.getSystem().displayMetrics.widthPixels
        var itemWidth = screenWidth / column

        //setAdapter
        val adapter = StoreItemAdapter(this, data, itemWidth, object : StoreItemAdapter.RecyclerViewHolder.OnClickListener {
            override fun onItemCartClick(position: Int) {
//                (data[position].itemPriceInt * item_store_txt_num.text.toString().toInt()).toString()

                if (data[position].itemNumber!=0) {
                    shoppingCartAdapter.datas.add(
                        ShoppingCartData(
                            itemImg = data[position].itemImg,
                            itemName = data[position].itemName,
                            itemPrice_int = data[position].itemPriceInt,
                            itemPrice_str = data[position].itemPrice,
                            itemNumber = data[position].itemNumber
//                        (data[position].itemPriceInt * data[position].itemNumber).toString(),
                        )
                    )
                    shoppingCartAdapter.notifyDataSetChanged()
                }
            }

            override fun onPlusItem(position: Int) {
                data[position].itemNumber += 1
            }

            override fun onMinusItem(position: Int) {
                data[position].itemNumber -= 1
            }

        })
        act_store_item_recyclerview.adapter = adapter


        //indicator
        val indicator = findViewById<View>(R.id.second_page_indicator) as LinePageIndicator
        indicator.setRecyclerView(act_store_item_recyclerview)


        //Note: pageColumn must be config
        indicator.setPageColumn(column)
//        indicator.setOnPageChangeListener(object : OnPageChangeListener() {
//            override fun onPageSelected(position: Int) {}
//            override fun onPageScrollStateChanged(state: Int) {}
//        })
    }

    fun getStoreItem(data: String) {

        val type = JSONObject().put("type", data)

        RequestToServer.service.requestStore(JsonParser.parseString(type.toString()) as JsonObject).enqueue(object : Callback<StoreResponse> {
            override fun onResponse(call: Call<StoreResponse>, response: Response<StoreResponse>) {
                if (response.isSuccessful) {
//                    val data = response.body()?.data
                    val list = mutableListOf<StoreData>()

                    for (storeitem in response.body()?.data!!) {
                        list.apply {
                            add(
                                StoreData(
                                    itemNumber = storeitem.item_number,
                                    itemPrice = storeitem.price,
                                    itemName = storeitem.name,
                                    itemImg = storeitem.photo,
                                    itemPriceInt = storeitem.price_int
                                )
                            )
                        }
                    }

                    configRecyclerView(3, 3, list)

                }
            }

            override fun onFailure(call: Call<StoreResponse>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }

        })
    }
}

