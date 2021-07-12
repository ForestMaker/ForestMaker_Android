package com.example.forestmaker.ui.reserve.Store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.ShoppingCartData
import com.example.forestmaker.ui.reserve.ShoppingCartAdapter
import com.example.forestmaker.ui.reserve.ShoppingCartViewHolder
import kotlinx.android.synthetic.main.activity_shopping_cart.*

class ShoppingCartActivity : AppCompatActivity() {

    lateinit var shoppingCartData: ArrayList<ShoppingCartData>
    lateinit var shoppingCartAdapter: ShoppingCartAdapter
    var receiveCartData = ArrayList<ShoppingCartData>()

    var dateTime = ""
    var address = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)

        shoppingCartData = intent.getParcelableArrayListExtra<ShoppingCartData>("shoppingCartList")!!
        dateTime = intent.getStringExtra("dateTime").toString()
        address = intent.getStringExtra("address").toString()

        shoppingCartAdapter = ShoppingCartAdapter(this,
        object : ShoppingCartViewHolder.onClickListener{
            override fun onClickItemDelete(position: Int) {
                shoppingCartAdapter.datas.removeAt(position)
                shoppingCartAdapter.notifyDataSetChanged()
                calculate()
            }

            override fun onPlusItem(position: Int) {
                shoppingCartAdapter.datas[position].itemNumber += 1
                
                shoppingCartAdapter.notifyDataSetChanged()
                calculate()
            }

            override fun onMinusItem(position: Int) {
                shoppingCartAdapter.datas[position].itemNumber -= 1
                shoppingCartAdapter.notifyDataSetChanged()
                calculate()
            }
        })

        act_shoppingcart_item_recyclerview.adapter = shoppingCartAdapter
        act_shoppingcart_item_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        shoppingCartAdapter.datas = shoppingCartData
        shoppingCartAdapter.notifyDataSetChanged()
        calculate()

        act_shoppingcart_btn_buy.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("shoppingCartList", shoppingCartAdapter.datas)
            intent.putExtra("totalPrice", calculate().toString())
            intent.putExtra("dateTime", dateTime)
            intent.putExtra("address", address)
            intent.putExtra("headCount", "")
            intent.putExtra("type", "상점")
            intent.putExtra("name", "ForestMaker Store")
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
    }

    fun calculate(): Int{
        var price = 0
        for (data in shoppingCartAdapter.datas) {
            price+=data.itemPrice_int*data.itemNumber
        }
        act_shoppingcart_totalPrice.text = price.toString() + "원"
        return price
    }
}