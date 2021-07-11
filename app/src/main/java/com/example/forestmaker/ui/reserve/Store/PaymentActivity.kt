package com.example.forestmaker.ui.reserve.Store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.ShoppingCartData
import com.example.forestmaker.server.RequestToServer
import com.example.forestmaker.server.data.MyPageResponse
import com.example.forestmaker.server.data.PaymentResponse
import com.example.forestmaker.ui.home.MyTreeActivity
import com.example.forestmaker.ui.reserve.Reservation.ReservationInfoActivity
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

    var user_name = ""
    var user_phone = ""
    var user_email = ""
    var total_mileage = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        getUserInfo()
        getIntentData()

        paymentAdapter = PaymentAdapter(this)
        act_payment_recyclerview.adapter = paymentAdapter
        act_payment_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        paymentAdapter.datas = shoppingCartData
        paymentAdapter.notifyDataSetChanged()

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
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this, MyTreeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    finish()
                }
            }
            dlg.start()
        }


        act_payment_btn_useMileage.isSelected = false

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

    }

    fun getUserInfo() {

        RequestToServer.service.requestMyInfo(JsonParser.parseString(JSONObject().put("id", "test@abc.com").toString()) as JsonObject).enqueue(object :Callback<MyPageResponse>{
            override fun onResponse(
                call: Call<MyPageResponse>,
                response: Response<MyPageResponse>
            ) {
                if (response.isSuccessful) {
                     user_name = response.body()?.nickname.toString()
                     user_phone =  response.body()?.phone.toString()
                     user_email = response.body()?.id.toString()
                     total_mileage = response.body()?.mileage!!

                    act_payment_txt_userName.text = user_name
                    act_payment_txt_userPhone.text = user_phone
                    act_payment_txt_userEmail.text = user_email
                    act_payment_txt_userMileage.text = total_mileage.toString()
                }
            }

            override fun onFailure(call: Call<MyPageResponse>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }

        })
    }

    fun getIntentData() {
        Log.e("받아온 totalPrice", intent.getStringExtra("totalPrice").toString())
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

    fun reserve() {

        val ReserveJsonData = JSONObject()

        ReserveJsonData.put("date", dateTime)
        ReserveJsonData.put("headcount", headCount)
        ReserveJsonData.put("name", name)
        ReserveJsonData.put("type", type)
        ReserveJsonData.put("address", address)

        val user = JSONObject()
        user.put("user_name", user_name)
        user.put("user_phone", user_phone)
        user.put("user_id", user_email)

        val payment = JSONObject()
        payment.put("item", shoppingCartData[0].itemName)
        payment.put("item_count", shoppingCartData[0].itemNumber)
        payment.put("total_count", shoppingCartData.size-1)
        payment.put("total_price", totalPrice)
        payment.put("use_mileage", act_payment_txt_use_mileage.text.toString().toInt())
        payment.put("real_total_price", (totalPrice.toInt()-act_payment_txt_use_mileage.text.toString().toInt()).toString())

        ReserveJsonData.put("user", user)
        ReserveJsonData.put("payment", payment)

        ReserveJsonData.put("finalmile", act_payment_txt_userMileage.text.toString().toInt() - act_payment_txt_use_mileage.text.toString().toInt() + 100)

        val body = JsonParser.parseString(ReserveJsonData.toString()) as JsonObject
        Log.e("sendData", body.toString())

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