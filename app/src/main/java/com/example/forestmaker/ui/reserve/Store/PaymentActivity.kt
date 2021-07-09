package com.example.forestmaker.ui.reserve.Store

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.ShoppingCartData
import com.example.forestmaker.ui.reserve.Reservation.ReservationInfoActivity
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
            val dlg = PaymentDialog(this)
            dlg.setOnClickedListener { content ->
                if (content == "GO") {
                    val intent = Intent(this, ReservationInfoActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    finish()
                }
            }
            dlg.start()
        }

        shoppingCartData = intent.getParcelableArrayListExtra<ShoppingCartData>("shoppingCartList")!!
        act_payment_txt_totalPrice.text = intent.getStringExtra("totalPrice")
        act_payment_txt_realTotalPrice.text = intent.getStringExtra("totalPrice")

        paymentAdapter = PaymentAdapter(this)
        act_payment_recyclerview.adapter = paymentAdapter
        act_payment_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        paymentAdapter.datas = shoppingCartData
        paymentAdapter.notifyDataSetChanged()

        act_payment_btn_useMileage.isSelected = false

        // 마일리지 전체 사용
        act_payment_btn_useMileageAll.setOnClickListener {
            act_payment_txt_use_mileage.text = act_payment_txt_userMileage.text.toString() + "P"
            act_payment_txt_realTotalPrice.text = (act_payment_txt_totalPrice.text.toString().toInt() - act_payment_txt_userMileage.text.toString().toInt()).toString()
        }

        act_payment_edit_useMileage.setOnClickListener {
            act_payment_btn_useMileage.isSelected = false
        }

        // 마일리지 부분 사용
        act_payment_btn_useMileage.setOnClickListener {
            if (act_payment_edit_useMileage.text.isNullOrBlank()) {
                act_payment_edit_useMileage.setText(0)
            }
            if (act_payment_edit_useMileage.text.toString().toInt() > act_payment_txt_userMileage.text.toString().toInt()) {
                Toast.makeText(this, "보유 마일리지보다 작은 값을 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else {
                act_payment_txt_use_mileage.text = act_payment_edit_useMileage.text.toString() + "P"
                act_payment_txt_realTotalPrice.text = (act_payment_txt_totalPrice.text.toString().toInt() - act_payment_edit_useMileage.text.toString().toInt()).toString()

                act_payment_btn_useMileage.isSelected = true
            }
        }

    }
}