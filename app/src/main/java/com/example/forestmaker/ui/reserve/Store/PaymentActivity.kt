package com.example.forestmaker.ui.reserve.Store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.ShoppingCartData
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {
    lateinit var shoppingCartData: ArrayList<ShoppingCartData>
    lateinit var paymentAdapter: PaymentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        act_payment_btn_back.setOnClickListener {
            finish()
        }

        act_payment_btn_pay.setOnClickListener {
            // 실제 결제
//            val intent = Intent(this, )
            finish()
        }
//        val bundle = intent.extras
////        bundle?.getSerializable("Obj");
//        shoppingCartData = bundle?.getParcelableArrayList("shoppingCartList")

        shoppingCartData = intent.getParcelableArrayListExtra<ShoppingCartData>("shoppingCartList")!!
        Log.d("data", shoppingCartData.toString())

        paymentAdapter = PaymentAdapter(this)
        act_payment_recyclerview.adapter = paymentAdapter
        act_payment_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        paymentAdapter.datas = shoppingCartData
        paymentAdapter.notifyDataSetChanged()

    }
}