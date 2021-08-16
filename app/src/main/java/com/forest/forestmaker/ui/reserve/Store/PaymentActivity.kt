package com.forest.forestmaker.ui.reserve.Store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.forest.forestmaker.MainActivity
import com.forest.forestmaker.R
import com.forest.forestmaker.data.ShoppingCartData
import com.forest.forestmaker.server.RequestToServer
import com.forest.forestmaker.server.data.MyPageResponse
import com.forest.forestmaker.server.data.PaymentResponse
import com.forest.forestmaker.ui.reserve.Reservation.ReservationInfoActivity
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_payment.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentActivity : AppCompatActivity() {
    lateinit var shoppingCartData: ArrayList<ShoppingCartData>
    lateinit var paymentAdapter: PaymentAdapter

    var totalPrice = ""
    var dateTime = ""
    var headCount = ""

    var type = ""
    var name = ""
    var address = ""
    var treecnt = 0

    var userName = ""
    var userPhone = ""
    var userEmail = ""
    var totalMileage = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        setIntentData()
        getUserInfo()
        setButton()
        setAdapter()

        act_payment_btn_useMileage.isSelected = false



    }

    private fun setIntentData() {
        userEmail = intent.getStringExtra("user_email").toString()
        totalPrice = intent.getStringExtra("totalPrice").toString()
        dateTime = intent.getStringExtra("dateTime").toString()
        headCount = intent.getStringExtra("headCount").toString()

        type = intent.getStringExtra("type").toString()
        name = intent.getStringExtra("name").toString()
        address = intent.getStringExtra("address").toString()

        shoppingCartData = intent.getParcelableArrayListExtra<ShoppingCartData>("shoppingCartList")!!

        //initial
        act_payment_txt_totalPrice.text = totalPrice
        act_payment_txt_realTotalPrice.text = totalPrice
    }

    private fun getUserInfo() {

        RequestToServer.service.requestMyInfo(JsonParser.parseString(JSONObject().put("id", userEmail).toString()) as JsonObject).enqueue(object :Callback<MyPageResponse>{
            override fun onResponse(
                call: Call<MyPageResponse>,
                response: Response<MyPageResponse>
            ) {
                if (response.isSuccessful) {
                    userName = response.body()?.nickname.toString()
                    userPhone =  response.body()?.phone.toString()
                    userEmail = response.body()?.id.toString()
                    totalMileage = response.body()?.mileage!!
                    treecnt = response.body()?.treecnt!!

                    act_payment_txt_userName.text = userName
                    act_payment_txt_userPhone.text = userPhone
                    act_payment_txt_userEmail.text = userEmail
                    act_payment_txt_userMileage.text = totalMileage.toString()
                }
            }

            override fun onFailure(call: Call<MyPageResponse>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }

        })
    }

    private fun setButton() {
        // 마일리지 전체 사용
        act_payment_btn_useMileageAll.setOnClickListener {
            act_payment_txt_use_mileage.text = act_payment_txt_userMileage.text.toString()
            act_payment_txt_realTotalPrice.text = (act_payment_txt_totalPrice.text.toString().toInt() - act_payment_txt_userMileage.text.toString().toInt()).toString()
            act_payment_edit_useMileage.setText(act_payment_txt_userMileage.text)
            act_payment_btn_useMileage.isSelected = true
        }

        act_payment_edit_useMileage.setOnClickListener {
            act_payment_btn_useMileage.isSelected = false
        }

        // 마일리지 부분 사용
        act_payment_btn_useMileage.setOnClickListener {
            if (act_payment_edit_useMileage.text.toString().toInt() > act_payment_txt_userMileage.text.toString().toInt()) {
                Toast.makeText(this, "보유 마일리지보다 작은 값을 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else {
                act_payment_txt_use_mileage.text = act_payment_edit_useMileage.text.toString()
                act_payment_txt_realTotalPrice.text = (totalPrice.toInt() - act_payment_edit_useMileage.text.toString().toInt()).toString()

                act_payment_btn_useMileage.isSelected = true
            }
        }

        act_payment_btn_back.setOnClickListener {
            finish()
        }

        act_payment_btn_pay.setOnClickListener {
            reserve()
            // 실제 결제
            val dlg = PaymentDialog(this)
            dlg.setOnClickedListener { content ->
                if (content == "GO") {
                    val intent = Intent(this, ReservationInfoActivity::class.java)
                    intent.putExtra("user_email", userEmail)
                    intent.putExtra("user_nickname", userName)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("nickname", userName)
                    intent.putExtra("email", userEmail)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                }
            }
            dlg.start()
        }
    }

    private fun setAdapter() {
        paymentAdapter = PaymentAdapter(this)
        act_payment_recyclerview.adapter = paymentAdapter
        act_payment_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        paymentAdapter.datas = shoppingCartData
        paymentAdapter.notifyDataSetChanged()
    }

    fun reserve() {

        val reserveJsonData = JSONObject()

        reserveJsonData.put("date", dateTime)
        reserveJsonData.put("headcount", headCount)
        reserveJsonData.put("name", name)
        reserveJsonData.put("type", type)
        reserveJsonData.put("address", address)

        val user = JSONObject()
        user.put("user_name", userName)
        user.put("user_phone", userPhone)
        user.put("user_id", userEmail)

        val payment = JSONObject()
        payment.put("item", shoppingCartData[0].itemName)
        payment.put("item_count", shoppingCartData[0].itemNumber)
        payment.put("total_count", shoppingCartData.size-1)
        payment.put("total_price", totalPrice)
        payment.put("use_mileage", act_payment_txt_use_mileage.text.toString().toInt())
        payment.put("real_total_price", (totalPrice.toInt()-act_payment_txt_use_mileage.text.toString().toInt()).toString())

        reserveJsonData.put("user", user)
        reserveJsonData.put("payment", payment)

        reserveJsonData.put("finalmile", act_payment_txt_userMileage.text.toString().toInt() - act_payment_txt_use_mileage.text.toString().toInt() + 100)

        if (type == "나무") {
            reserveJsonData.put("finaltree", treecnt+shoppingCartData.size)
        } else {
            reserveJsonData.put("finaltree", treecnt)
        }

        reserveJsonData.put("state", 0)

        val body = JsonParser.parseString(reserveJsonData.toString()) as JsonObject

        RequestToServer.service.requestPayment(body).enqueue(object :Callback<PaymentResponse>{

            override fun onResponse(call: Call<PaymentResponse>, response: Response<PaymentResponse>) {
                if (response.isSuccessful) {
                    Log.e("success", response.body().toString())
                }
            }

            override fun onFailure(call: Call<PaymentResponse>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }

        })
    }
}