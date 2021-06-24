package com.example.forestmaker.ui.Reserve.Store

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.ShoppingCartData
import com.example.forestmaker.data.StoreItemData
import com.example.forestmaker.data.storeDatas
import com.example.forestmaker.ui.Reserve.*
import com.hhl.gridpagersnaphelper.GridPagerSnapHelper
import com.hhl.recyclerviewindicator.LinePageIndicator
import kotlinx.android.synthetic.main.activity_store.*
import kotlinx.android.synthetic.main.item_store_item.*


class StoreActivity : AppCompatActivity() {

    var storeItemData = ArrayList<StoreItemData>()
    var shoppingCartData = ArrayList<ShoppingCartData>()
    lateinit var shoppingCartAdapter: ShoppingCartAdapter
    lateinit var storeItemAdapter: StoreItemAdapter

    var treeData = mutableListOf<storeDatas>()
    var tonicData = mutableListOf<storeDatas>()
    var rentalData = mutableListOf<storeDatas>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        // filter initialize
        act_store_filter_btn_all.isSelected = true
        act_store_filter_btn_tree.isSelected = false
        act_store_filter_btn_supplements.isSelected = false
        act_store_filter_btn_rent.isSelected = false

        act_store_btn_back.setOnClickListener {
            finish()
        }

        act_store_btn_buy.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
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

            }
        )


        act_store_shoppinglist_recyclerview.adapter = shoppingCartAdapter
        act_store_shoppinglist_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        shoppingCartData.clear()
        loadItemData()

        configRecyclerView(2, 3, treeData)


        act_store_filter_btn_all.setOnClickListener {
            act_store_filter_btn_all.isSelected = true
            act_store_filter_btn_tree.isSelected = false
            act_store_filter_btn_supplements.isSelected = false
            act_store_filter_btn_rent.isSelected = false
        }
        act_store_filter_btn_tree.setOnClickListener {
            act_store_filter_btn_all.isSelected = false
            act_store_filter_btn_tree.isSelected = true
            act_store_filter_btn_supplements.isSelected = false
            act_store_filter_btn_rent.isSelected = false

            configRecyclerView(2, 3, treeData)
        }
        act_store_filter_btn_supplements.setOnClickListener {
            act_store_filter_btn_all.isSelected = false
            act_store_filter_btn_tree.isSelected = false
            act_store_filter_btn_supplements.isSelected = true
            act_store_filter_btn_rent.isSelected = false

            configRecyclerView(2, 3, tonicData)
        }
        act_store_filter_btn_rent.setOnClickListener {
            act_store_filter_btn_all.isSelected = false
            act_store_filter_btn_tree.isSelected = false
            act_store_filter_btn_supplements.isSelected = false
            act_store_filter_btn_rent.isSelected = true

            configRecyclerView(2, 3, rentalData)
        }

    }

    fun configRecyclerView(row: Int, column: Int, data: MutableList<storeDatas>) {
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
                (data[position].itemPrice.toInt() * item_store_txt_num.text.toString().toInt()).toString()

                shoppingCartAdapter.datas.add(
                    ShoppingCartData(
                        data[position].itemName,
                        (data[position].itemPrice.toInt() * data[position].itemNumber).toString(),
                        data[position].itemNumber.toString()
                    )
                )
                shoppingCartAdapter.notifyDataSetChanged()
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

    fun loadItemData() {
        treeData.apply {
            add(
                storeDatas(
                    "https://t1.daumcdn.net/cfile/tistory/99554C425ACCC63113",
                    "상추1",
                    "900",
                    1
                )
            )
            add(
                storeDatas(
                    "http://health.chosun.com/site/data/img_dir/2020/08/28/2020082802518_0.jpg",
                    "새송이 버섯2",
                    "4850",
                    1
                )
            )
            add(
                storeDatas(
                    "https://lh3.googleusercontent.com/proxy/REK1sc1jnF6zm8ViIGaQoo5sV5zLeZoI8qylWl0-9FJc243EO43hTQGakRX6Zxm_1hHGxrJpK5Wtvv347aXoVURhv-3YJYPWmOIbwTzQx9OgfxGikLc",
                    "꼬마 토마토3",
                    "5500",
                    1
                )
            )
            add(
                storeDatas(
                    "https://t1.daumcdn.net/cfile/tistory/99554C425ACCC63113",
                    "상추4",
                    "900",
                    1
                )
            )
            add(
                storeDatas(
                    "http://health.chosun.com/site/data/img_dir/2020/08/28/2020082802518_0.jpg",
                    "새송이 버섯5",
                    "4850",
                    1
                )
            )
            add(
                storeDatas(
                    "https://lh3.googleusercontent.com/proxy/REK1sc1jnF6zm8ViIGaQoo5sV5zLeZoI8qylWl0-9FJc243EO43hTQGakRX6Zxm_1hHGxrJpK5Wtvv347aXoVURhv-3YJYPWmOIbwTzQx9OgfxGikLc",
                    "꼬마 토마토6",
                    "5500",
                    1
                )
            )
            add(
                storeDatas(
                    "https://t1.daumcdn.net/cfile/tistory/99554C425ACCC63113",
                    "상추7",
                    "900",
                    1
                )
            )
            add(
                storeDatas(
                    "http://health.chosun.com/site/data/img_dir/2020/08/28/2020082802518_0.jpg",
                    "새송이 버섯8",
                    "4850",
                    1
                )
            )
            add(
                storeDatas(
                    "https://lh3.googleusercontent.com/proxy/REK1sc1jnF6zm8ViIGaQoo5sV5zLeZoI8qylWl0-9FJc243EO43hTQGakRX6Zxm_1hHGxrJpK5Wtvv347aXoVURhv-3YJYPWmOIbwTzQx9OgfxGikLc",
                    "꼬마 토마토9",
                    "5500",
                    1
                )
            )
            add(
                storeDatas(
                    "https://t1.daumcdn.net/cfile/tistory/99554C425ACCC63113",
                    "상추10",
                    "900",
                    1
                )
            )
            add(
                storeDatas(
                    "http://health.chosun.com/site/data/img_dir/2020/08/28/2020082802518_0.jpg",
                    "새송이 버섯11",
                    "4850",
                    1
                )
            )
            add(
                storeDatas(
                    "https://lh3.googleusercontent.com/proxy/REK1sc1jnF6zm8ViIGaQoo5sV5zLeZoI8qylWl0-9FJc243EO43hTQGakRX6Zxm_1hHGxrJpK5Wtvv347aXoVURhv-3YJYPWmOIbwTzQx9OgfxGikLc",
                    "꼬마 토마토12",
                    "5500",
                    1
                )
            )
            add(
                storeDatas(
                    "https://t1.daumcdn.net/cfile/tistory/99554C425ACCC63113",
                    "상추13",
                    "900",
                    1
                )
            )
            add(
                storeDatas(
                    "http://health.chosun.com/site/data/img_dir/2020/08/28/2020082802518_0.jpg",
                    "새송이 버섯14",
                    "4850",
                    1
                )
            )
            add(
                storeDatas(
                    "https://lh3.googleusercontent.com/proxy/REK1sc1jnF6zm8ViIGaQoo5sV5zLeZoI8qylWl0-9FJc243EO43hTQGakRX6Zxm_1hHGxrJpK5Wtvv347aXoVURhv-3YJYPWmOIbwTzQx9OgfxGikLc",
                    "꼬마 토마토15",
                    "5500",
                    1
                )
            )
        }


        tonicData.apply {
            add(
                storeDatas(
                    "http://m.cereshome.co.kr/web/product/big/202008/ac09b1e644832b0f38b7c8a7d514b9c0.jpg",
                    "식물 영양1",
                    "3000",
                    1
                )
            )
            add(
                storeDatas(
                    "http://image.auction.co.kr/itemimage/16/70/04/1670048b61.jpg",
                    "수간주사",
                    "6000",
                    1
                )
            )
            add(
                storeDatas(
                    "https://shop.shouse.garden/data/goods/97/2021/01/534_tmp_c387fb11ec2a2dfef160650f808808117028view.jpg",
                    "비료영양",
                    "6000",
                    1
                )
            )
            add(
                storeDatas(
                    "https://lh3.googleusercontent.com/proxy/MqSiwkbiLv5ZkI9KnpXr9ODymPFiRqutrBdP5pJr8MmK_zHVHH2kwd27MWcoZ753ATpCzY75ePlGhq0qRjDGodbt8IbhrJKTX2GiNjVnNjxAcvVYDUtx54QBPvT4xOpB",
                    "비료영양2",
                    "8000",
                    1
                )
            )
            add(
                storeDatas(
                    "https://lh3.googleusercontent.com/proxy/MqSiwkbiLv5ZkI9KnpXr9ODymPFiRqutrBdP5pJr8MmK_zHVHH2kwd27MWcoZ753ATpCzY75ePlGhq0qRjDGodbt8IbhrJKTX2GiNjVnNjxAcvVYDUtx54QBPvT4xOpB",
                    "비료영양2",
                    "8000",
                    1
                )
            )
        }

        rentalData.apply {
            add(
                storeDatas(
                    "http://image.auction.co.kr/itemimage/19/ae/06/19ae0682d6.jpg",
                    "모종삽10",
                    "1000",
                    1
                )
            )
            add(
                storeDatas(
                    "http://image.auction.co.kr/itemimage/19/ae/06/19ae0682d6.jpg",
                    "모종삽10",
                    "1000",1
                )
            )
            add(
                storeDatas(
                    "http://image.auction.co.kr/itemimage/19/ae/06/19ae0682d6.jpg",
                    "모종삽10",
                    "1000",1
                )
            )
            add(
                storeDatas(
                    "http://image.auction.co.kr/itemimage/19/ae/06/19ae0682d6.jpg",
                    "모종삽10",
                    "1000",1
                )
            )
        }


        storeItemData.apply {
            add(
                StoreItemData(
                    "store_tree",
                    treeData
                )
            )
            add(
                StoreItemData(
                    "store_tonic",
                    tonicData
                )
            )
            add(
                StoreItemData(
                    "store_rental",
                    rentalData
                )
            )
        }
    }
}

