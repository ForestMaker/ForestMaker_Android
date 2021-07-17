package com.forest.forestmaker.ui.reserve.Store

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.forest.forestmaker.R
import com.forest.forestmaker.data.ShoppingCartData
import com.forest.forestmaker.data.StoreData
import com.forest.forestmaker.server.RequestToServer
import com.forest.forestmaker.server.data.StoreItem
import com.forest.forestmaker.ui.reserve.*
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
    var user_email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        val type = JSONObject().put("type", "tree_data")
        user_email = intent.getStringExtra("user_email").toString()
        act_store_text_reserveTime.text = intent.getStringExtra("dateTime")
        act_store_text_reserveAddress.text = intent.getStringExtra("address")

        // filter initialize
        act_store_filter_btn_all.isSelected = true
        act_store_filter_btn_tree.isSelected = false
        act_store_filter_btn_supplements.isSelected = false
        act_store_filter_btn_rent.isSelected = false

        act_store_btn_back.setOnClickListener {
            finish()
        }

        act_store_btn_buy.setOnClickListener {
            if (shoppingCartAdapter.datas.size > 0) {
                val intent = Intent(this, ShoppingCartActivity::class.java)
                intent.putExtra("shoppingCartList", shoppingCartAdapter.datas)
                intent.putExtra("dateTime", act_store_text_reserveTime.text.toString())
                intent.putExtra("address", act_store_text_reserveAddress.text.toString())
                intent.putExtra("user_email", user_email)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "장바구니에 물품을 담아주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        shoppingCartAdapter = ShoppingCartAdapter(
            this,
            object : ShoppingCartViewHolder.onClickListener {
                override fun onClickItemDelete(position: Int) {

                    shoppingCartAdapter.datas.removeAt(position)
                    shoppingCartAdapter.notifyDataSetChanged()
                }
                override fun onPlusItem(position: Int) {}
                override fun onMinusItem(position: Int) {}
            }
        )

        getStoreTree()

        act_store_filter_btn_all.setOnClickListener {
            act_store_filter_btn_all.isSelected = true
            act_store_filter_btn_tree.isSelected = false
            act_store_filter_btn_supplements.isSelected = false
            act_store_filter_btn_rent.isSelected = false

            getStoreTree()
        }

        act_store_filter_btn_tree.setOnClickListener {
            act_store_filter_btn_all.isSelected = false
            act_store_filter_btn_tree.isSelected = true
            act_store_filter_btn_supplements.isSelected = false
            act_store_filter_btn_rent.isSelected = false

            getStoreTree()
        }

        act_store_filter_btn_supplements.setOnClickListener {
            act_store_filter_btn_all.isSelected = false
            act_store_filter_btn_tree.isSelected = false
            act_store_filter_btn_supplements.isSelected = true
            act_store_filter_btn_rent.isSelected = false

            getStoreTonic()
        }
        act_store_filter_btn_rent.setOnClickListener {
            act_store_filter_btn_all.isSelected = false
            act_store_filter_btn_tree.isSelected = false
            act_store_filter_btn_supplements.isSelected = false
            act_store_filter_btn_rent.isSelected = true

            getStoreRental()
        }

    }

    fun configRecyclerView(row: Int, column: Int, data: MutableList<StoreData>) {

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
    }

    fun getStoreTree() {
        RequestToServer.service.requestStoreTree().enqueue(object : Callback<ArrayList<StoreItem>> {
            override fun onResponse(call: Call<ArrayList<StoreItem>>, response: Response<ArrayList<StoreItem>>) {
                if (response.isSuccessful) {
//                    val data = response.body()?.data
                    val list = mutableListOf<StoreData>()

                    for (storeitem in response.body()!!) {
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

            override fun onFailure(call: Call<ArrayList<StoreItem>>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }

        })
    }

    fun getStoreTonic() {

        RequestToServer.service.requestStoreTonic().enqueue(object : Callback<ArrayList<StoreItem>> {
            override fun onResponse(call: Call<ArrayList<StoreItem>>, response: Response<ArrayList<StoreItem>>) {
                if (response.isSuccessful) {
//                    val data = response.body()?.data
                    val list = mutableListOf<StoreData>()

                    for (storeitem in response.body()!!) {
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

            override fun onFailure(call: Call<ArrayList<StoreItem>>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }

        })
    }

    fun getStoreRental() {
        RequestToServer.service.requestStoreRantal().enqueue(object : Callback<ArrayList<StoreItem>> {
            override fun onResponse(call: Call<ArrayList<StoreItem>>, response: Response<ArrayList<StoreItem>>) {
                if (response.isSuccessful) {
//                    val data = response.body()?.data
                    val list = mutableListOf<StoreData>()

                    for (storeitem in response.body()!!) {
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

            override fun onFailure(call: Call<ArrayList<StoreItem>>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }

        })
    }
}

