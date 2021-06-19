package com.example.forestmaker.ui.Reserve

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.ShoppingCartData
import com.example.forestmaker.data.StoreItemData
import com.example.forestmaker.data.storeDatas
import com.hhl.gridpagersnaphelper.GridPagerSnapHelper
import com.hhl.recyclerviewindicator.LinePageIndicator
import kotlinx.android.synthetic.main.activity_store.*
import kotlinx.android.synthetic.main.item_store_item.*


class StoreActivity : AppCompatActivity() {

    var storeItemData = ArrayList<StoreItemData>()
    var shoppingCartData = mutableListOf<ShoppingCartData>()
    lateinit var storeItemAdapter: StoreItemAdapter
    lateinit var shoppingCartAdapter: ShoppingCartAdapter

    var treeData = mutableListOf<storeDatas>()
    var tonicData = mutableListOf<storeDatas>()
    var rentalData = mutableListOf<storeDatas>()

    @SuppressLint("WrongConstant")
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

        storeItemAdapter = StoreItemAdapter(
            this,
            object : StoreItemViewHolder.onClickListener{
//                override fun onItemPlusClick(position: Int) {
//                    item_store_txt_num.text = (item_store_txt_num.text.toString().toInt() + 1).toString()
//                    Log.d("click plus", item_store_txt_num.text as String)
//                }
//
//                override fun onItemMinusClick(position: Int) {
//                    item_store_txt_num.text = (item_store_txt_num.text.toString().toInt() - 1).toString()
//                    Log.d("click minus", item_store_txt_num.text as String)
//
//                }

                override fun onItemCartClick(position: Int) {

//                    Log.d("click cart", storeItemData[position].itemName+"  "+
//                        (storeItemData[position].itemPrice.toInt() * item_store_txt_num.text.toString().toInt()).toString())
//
//                    shoppingCartAdapter.datas.add(
//                        ShoppingCartData(
//                            storeItemData[position].itemName,
//                            (storeItemData[position].itemPrice.toInt() * item_store_txt_num.text.toString().toInt()).toString()
//                        )
//                    )
//                    shoppingCartAdapter.notifyDataSetChanged()

                }

            }
        )

        shoppingCartAdapter = ShoppingCartAdapter(
            this,
            object : ShoppingCartViewHolder.onClickListener{
                override fun onClickItemDelete(position: Int) {

                    shoppingCartAdapter.datas.removeAt(position)
                    shoppingCartAdapter.notifyDataSetChanged()
                }

            }
        )

//        item_store_horizontal_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        configRecyclerView(2, 3)


//        val gridLayoutManager  = GridLayoutManager(this, 2, HORIZONTAL, false)
//        gridLayoutManager.orientation = HORIZONTAL
//        act_store_item_recyclerview.adapter = storeItemAdapter
//        act_store_item_recyclerview.layoutManager = GridLayoutManager(this, 2, HORIZONTAL, false)
//        act_store_item_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

//
//        val gridLayoutManager =
//            GridLayoutManager(applicationContext, 2, LinearLayoutManager.HORIZONTAL, false)
//        act_store_item_recyclerview.layoutManager = gridLayoutManager
//
//
//        val gridPagerSnapHelper = GridPagerSnapHelper()
//        gridPagerSnapHelper.setRow(2).setColumn(3)
//        gridPagerSnapHelper.attachToRecyclerView(act_store_item_recyclerview)
//
//        val displayMetrics = DisplayMetrics()
//        windowManager.defaultDisplay.getMetrics(displayMetrics)
//
//        var width = displayMetrics.widthPixels
//
//        val itemWidth: Int = width / 3


        act_store_shoppinglist_recyclerview.adapter = shoppingCartAdapter
        act_store_shoppinglist_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        shoppingCartData.clear()
        loadItemData()

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
        }
        act_store_filter_btn_supplements.setOnClickListener {
            act_store_filter_btn_all.isSelected = false
            act_store_filter_btn_tree.isSelected = false
            act_store_filter_btn_supplements.isSelected = true
            act_store_filter_btn_rent.isSelected = false
        }
        act_store_filter_btn_rent.setOnClickListener {
            act_store_filter_btn_all.isSelected = false
            act_store_filter_btn_tree.isSelected = false
            act_store_filter_btn_supplements.isSelected = false
            act_store_filter_btn_rent.isSelected = true
        }



    }

    private fun configRecyclerView(row: Int, column: Int) {
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
        val itemWidth = screenWidth / column


        //setAdapter
        val adapter = RecyclerViewAdapter(this, treeData, itemWidth, object : RecyclerViewAdapter.RecyclerViewHolder.OnClickListener{
            override fun onItemCartClick(position: Int) {
                (treeData[position].itemPrice.toInt() * item_store_txt_num.text.toString().toInt()).toString()

                shoppingCartAdapter.datas.add(
                    ShoppingCartData(
                        treeData[position].itemName,
                        (treeData[position].itemPrice.toInt() * item_store_txt_num.text.toString().toInt()).toString()
                    )
                )
                shoppingCartAdapter.notifyDataSetChanged()
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
                    "900"
                )
            )
            add(
                storeDatas(
                    "http://health.chosun.com/site/data/img_dir/2020/08/28/2020082802518_0.jpg",
                    "새송이 버섯2",
                    "4850"
                )
            )
            add(
                storeDatas(
                    "https://lh3.googleusercontent.com/proxy/REK1sc1jnF6zm8ViIGaQoo5sV5zLeZoI8qylWl0-9FJc243EO43hTQGakRX6Zxm_1hHGxrJpK5Wtvv347aXoVURhv-3YJYPWmOIbwTzQx9OgfxGikLc",
                    "꼬마 토마토3",
                    "5500"
                )
            )
            add(
                storeDatas(
                    "https://t1.daumcdn.net/cfile/tistory/99554C425ACCC63113",
                    "상추4",
                    "900"
                )
            )
            add(
                storeDatas(
                    "http://health.chosun.com/site/data/img_dir/2020/08/28/2020082802518_0.jpg",
                    "새송이 버섯5",
                    "4850"
                )
            )
            add(
                storeDatas(
                    "https://lh3.googleusercontent.com/proxy/REK1sc1jnF6zm8ViIGaQoo5sV5zLeZoI8qylWl0-9FJc243EO43hTQGakRX6Zxm_1hHGxrJpK5Wtvv347aXoVURhv-3YJYPWmOIbwTzQx9OgfxGikLc",
                    "꼬마 토마토6",
                    "5500"
                )
            )
            add(
                storeDatas(
                    "https://t1.daumcdn.net/cfile/tistory/99554C425ACCC63113",
                    "상추7",
                    "900"
                )
            )
            add(
                storeDatas(
                    "http://health.chosun.com/site/data/img_dir/2020/08/28/2020082802518_0.jpg",
                    "새송이 버섯8",
                    "4850"
                )
            )
            add(
                storeDatas(
                    "https://lh3.googleusercontent.com/proxy/REK1sc1jnF6zm8ViIGaQoo5sV5zLeZoI8qylWl0-9FJc243EO43hTQGakRX6Zxm_1hHGxrJpK5Wtvv347aXoVURhv-3YJYPWmOIbwTzQx9OgfxGikLc",
                    "꼬마 토마토9",
                    "5500"
                )
            )
            add(
                storeDatas(
                    "https://t1.daumcdn.net/cfile/tistory/99554C425ACCC63113",
                    "상추10",
                    "900"
                )
            )
            add(
                storeDatas(
                    "http://health.chosun.com/site/data/img_dir/2020/08/28/2020082802518_0.jpg",
                    "새송이 버섯11",
                    "4850"
                )
            )
            add(
                storeDatas(
                    "https://lh3.googleusercontent.com/proxy/REK1sc1jnF6zm8ViIGaQoo5sV5zLeZoI8qylWl0-9FJc243EO43hTQGakRX6Zxm_1hHGxrJpK5Wtvv347aXoVURhv-3YJYPWmOIbwTzQx9OgfxGikLc",
                    "꼬마 토마토12",
                    "5500"
                )
            )
            add(
                storeDatas(
                    "https://t1.daumcdn.net/cfile/tistory/99554C425ACCC63113",
                    "상추13",
                    "900"
                )
            )
            add(
                storeDatas(
                    "http://health.chosun.com/site/data/img_dir/2020/08/28/2020082802518_0.jpg",
                    "새송이 버섯14",
                    "4850"
                )
            )
            add(
                storeDatas(
                    "https://lh3.googleusercontent.com/proxy/REK1sc1jnF6zm8ViIGaQoo5sV5zLeZoI8qylWl0-9FJc243EO43hTQGakRX6Zxm_1hHGxrJpK5Wtvv347aXoVURhv-3YJYPWmOIbwTzQx9OgfxGikLc",
                    "꼬마 토마토15",
                    "5500"
                )
            )
        }


        tonicData.apply {
            add(
                storeDatas(
                    "https://m.verandarecipe.com/web/product/big/201901/4607b4d13f9c5bafd6cad078dec3f8c6.jpg",
                    "식물 영양1",
                    "6000"
                )
            )
            add(
                storeDatas(
                    "https://m.verandarecipe.com/web/product/big/201901/4607b4d13f9c5bafd6cad078dec3f8c6.jpg",
                    "식물 영양2",
                    "6000"
                )
            )
            add(
                storeDatas(
                    "https://m.verandarecipe.com/web/product/big/201901/4607b4d13f9c5bafd6cad078dec3f8c6.jpg",
                    "식물 영양3",
                    "6000"
                )
            )
            add(
                storeDatas(
                    "https://m.verandarecipe.com/web/product/big/201901/4607b4d13f9c5bafd6cad078dec3f8c6.jpg",
                    "식물 영양1",
                    "6000"
                )
            )
            add(
                storeDatas(
                    "https://m.verandarecipe.com/web/product/big/201901/4607b4d13f9c5bafd6cad078dec3f8c6.jpg",
                    "식물 영양2",
                    "6000"
                )
            )
            add(
                storeDatas(
                    "https://m.verandarecipe.com/web/product/big/201901/4607b4d13f9c5bafd6cad078dec3f8c6.jpg",
                    "식물 영양3",
                    "6000"
                )
            )
        }

        rentalData.apply {
            add(
                storeDatas(
                    "http://image.auction.co.kr/itemimage/19/ae/06/19ae0682d6.jpg",
                    "모종삽10",
                    "1000"
                )
            )
            add(
                storeDatas(
                    "http://image.auction.co.kr/itemimage/19/ae/06/19ae0682d6.jpg",
                    "모종삽10",
                    "1000"
                )
            )
            add(
                storeDatas(
                    "http://image.auction.co.kr/itemimage/19/ae/06/19ae0682d6.jpg",
                    "모종삽10",
                    "1000"
                )
            )
            add(
                storeDatas(
                    "http://image.auction.co.kr/itemimage/19/ae/06/19ae0682d6.jpg",
                    "모종삽10",
                    "1000"
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
        Log.d("data", storeItemData.toString())
        storeItemAdapter.s = treeData
        storeItemAdapter.datas = storeItemData
        storeItemAdapter.notifyDataSetChanged()

        storeItemAdapter.s = treeData
    }
}

