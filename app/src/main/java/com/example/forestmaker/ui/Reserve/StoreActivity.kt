package com.example.forestmaker.ui.Reserve

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import android.widget.GridLayout.HORIZONTAL
import android.widget.HorizontalScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.forestmaker.R
import com.example.forestmaker.data.ShoppingCartData
import com.example.forestmaker.data.StoreItemData
import kotlinx.android.synthetic.main.activity_store.*
import kotlinx.android.synthetic.main.item_store_item.*


class StoreActivity : AppCompatActivity() {

    var storeItemData = mutableListOf<StoreItemData>()
    var shoppingCartData = mutableListOf<ShoppingCartData>()
    lateinit var storeItemAdapter: StoreItemAdapter
    lateinit var shoppingCartAdapter: ShoppingCartAdapter

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        // filter initialize
        act_store_filter_btn_all.isSelected = true
        act_store_filter_btn_tree.isSelected = false
        act_store_filter_btn_supplements.isSelected = false
        act_store_filter_btn_rent.isSelected = false

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

                    Log.d("click cart", storeItemData[position].itemName+"  "+
                        (storeItemData[position].itemPrice.toInt() * item_store_txt_num.text.toString().toInt()).toString())

                    shoppingCartAdapter.datas.add(
                        ShoppingCartData(
                            storeItemData[position].itemName,
                            (storeItemData[position].itemPrice.toInt() * item_store_txt_num.text.toString().toInt()).toString()
                        )
                    )
                    shoppingCartAdapter.notifyDataSetChanged()

                }

            }
        )

        shoppingCartAdapter = ShoppingCartAdapter(
            this,
            object : ShoppingCartViewHolder.onClickListener{
                override fun onClickItem(position: Int) {
                    // 혹여나 나중 삭제 및 수정
                }

            }
        )



        act_store_item_recyclerview.adapter = storeItemAdapter
        act_store_item_recyclerview.layoutManager = GridLayoutManager(this, 2, HORIZONTAL, false)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(act_store_item_recyclerview)


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

    fun loadItemData() {
        storeItemData.apply {
            add(
                StoreItemData(
                    0,
                    "https://lh3.googleusercontent.com/proxy/REK1sc1jnF6zm8ViIGaQoo5sV5zLeZoI8qylWl0-9FJc243EO43hTQGakRX6Zxm_1hHGxrJpK5Wtvv347aXoVURhv-3YJYPWmOIbwTzQx9OgfxGikLc",
                    "꼬마 토마토1",
                    "5500"
                )
            )
            add(
                StoreItemData(
                    0,
                    "https://t1.daumcdn.net/cfile/tistory/99554C425ACCC63113",
                    "상추2",
                    "900"
                )
            )
            add(
                StoreItemData(
                    0,
                    "http://health.chosun.com/site/data/img_dir/2020/08/28/2020082802518_0.jpg",
                    "새송이 버섯3",
                    "4850"
                )
            )
            add(
                StoreItemData(
                    0,
                    "https://m.verandarecipe.com/web/product/big/201901/4607b4d13f9c5bafd6cad078dec3f8c6.jpg",
                    "식물 영양4",
                    "6000"
                )
            )
            add(
                StoreItemData(
                    0,
                    "http://image.auction.co.kr/itemimage/19/ae/06/19ae0682d6.jpg",
                    "모종삽5",
                    "1000"
                )
            )
            add(
                StoreItemData(
                    0,
                    "https://lh3.googleusercontent.com/proxy/REK1sc1jnF6zm8ViIGaQoo5sV5zLeZoI8qylWl0-9FJc243EO43hTQGakRX6Zxm_1hHGxrJpK5Wtvv347aXoVURhv-3YJYPWmOIbwTzQx9OgfxGikLc",
                    "꼬마 토마토6",
                    "5500"
                )
            )
            add(
                StoreItemData(
                    0,
                    "https://t1.daumcdn.net/cfile/tistory/99554C425ACCC63113",
                    "상추7",
                    "900"
                )
            )
            add(
                StoreItemData(
                    0,
                    "http://health.chosun.com/site/data/img_dir/2020/08/28/2020082802518_0.jpg",
                    "새송이 버섯8",
                    "4850"
                )
            )
            add(
                StoreItemData(
                    0,
                    "https://m.verandarecipe.com/web/product/big/201901/4607b4d13f9c5bafd6cad078dec3f8c6.jpg",
                    "식물 영양9",
                    "6000"
                )
            )
            add(
                StoreItemData(
                    0,
                    "http://image.auction.co.kr/itemimage/19/ae/06/19ae0682d6.jpg",
                    "모종삽10",
                    "1000"
                )
            )
            add(
                StoreItemData(
                    0,
                    "https://lh3.googleusercontent.com/proxy/REK1sc1jnF6zm8ViIGaQoo5sV5zLeZoI8qylWl0-9FJc243EO43hTQGakRX6Zxm_1hHGxrJpK5Wtvv347aXoVURhv-3YJYPWmOIbwTzQx9OgfxGikLc",
                    "꼬마 토마토11",
                    "5500"
                )
            )
            add(
                StoreItemData(
                    0,
                    "https://t1.daumcdn.net/cfile/tistory/99554C425ACCC63113",
                    "상추12",
                    "900"
                )
            )
            add(
                StoreItemData(
                    0,
                    "http://health.chosun.com/site/data/img_dir/2020/08/28/2020082802518_0.jpg",
                    "새송이 버섯13",
                    "4850"
                )
            )
            add(
                StoreItemData(
                    0,
                    "https://m.verandarecipe.com/web/product/big/201901/4607b4d13f9c5bafd6cad078dec3f8c6.jpg",
                    "식물 영양14",
                    "6000"
                )
            )
            add(
                StoreItemData(
                    0,
                    "http://image.auction.co.kr/itemimage/19/ae/06/19ae0682d6.jpg",
                    "모종삽15",
                    "1000"
                )
            )
            add(
                StoreItemData(
                    0,
                    "https://lh3.googleusercontent.com/proxy/REK1sc1jnF6zm8ViIGaQoo5sV5zLeZoI8qylWl0-9FJc243EO43hTQGakRX6Zxm_1hHGxrJpK5Wtvv347aXoVURhv-3YJYPWmOIbwTzQx9OgfxGikLc",
                    "꼬마 토마토16",
                    "5500"
                )
            )
            add(
                StoreItemData(
                    0,
                    "https://t1.daumcdn.net/cfile/tistory/99554C425ACCC63113",
                    "상추17",
                    "900"
                )
            )
            add(
                StoreItemData(
                    0,
                    "http://health.chosun.com/site/data/img_dir/2020/08/28/2020082802518_0.jpg",
                    "새송이 버섯18",
                    "4850"
                )
            )
            add(
                StoreItemData(
                    0,
                    "https://m.verandarecipe.com/web/product/big/201901/4607b4d13f9c5bafd6cad078dec3f8c6.jpg",
                    "식물 영양19",
                    "6000"
                )
            )
            add(
                StoreItemData(
                    0,
                    "http://image.auction.co.kr/itemimage/19/ae/06/19ae0682d6.jpg",
                    "모종삽20",
                    "1000"
                )
            )
        }

        storeItemAdapter.datas = storeItemData
        Log.d("data size", storeItemAdapter.datas.size.toString())
        storeItemAdapter.notifyDataSetChanged()
    }
}