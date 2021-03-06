package com.forest.forestmaker.ui.reserve.Reservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.forest.forestmaker.MainActivity
import com.forest.forestmaker.R
import com.forest.forestmaker.data.ReservationData
import com.forest.forestmaker.server.RequestToServer
import com.forest.forestmaker.server.data.ReserveResponse
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_reservation_info.*
import kotlinx.android.synthetic.main.bottom_sheet_reservation.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReservationInfoActivity : AppCompatActivity() {

    var reservationDatas = mutableListOf<ReservationData>()
    var reservationAllData = ArrayList<ReserveResponse>()
    lateinit var reservationAdapter: ReservationAdapter

    var userNickName = ""
    var userEmail = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_info)

        setIntentData()
        setButton()
        setAdapter()
        getData()
    }

    private fun setIntentData() {
        userEmail = intent.getStringExtra("user_email").toString()
        userNickName = intent.getStringExtra("user_nickname").toString()
    }

    private fun setButton() {
        act_reservation_btn_back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("nickname", userNickName)
            intent.putExtra("email", userEmail)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        }
    }

    private fun setAdapter() {
        reservationAdapter = ReservationAdapter(
            this,
            object : ReservationViewHolder.OnClickListener {
                override fun ReservationDetailClick(position: Int) {

                    bottom_sheet_reservation_dateTime.text = reservationAllData[position].date
                    bottom_sheet_reservation_address.text = reservationAllData[position].address
                    bottom_sheet_reservation_user_name.text =
                        reservationAllData[position].user!!.user_name
                    bottom_sheet_reservation_user_phone.text =
                        reservationAllData[position].user!!.user_phone
                    bottom_sheet_reservation_user_email.text =
                        reservationAllData[position].user!!.user_id

                    bottom_sheet_reservation_item.text = reservationAllData[position].payment!!.item
                    bottom_sheet_reservation_item_count.text =
                        reservationAllData[position].payment!!.item_count.toString() + "??? ???"
                    bottom_sheet_reservation_item_totalCount.text =
                        reservationAllData[position].payment!!.total_count.toString() + "???"
                    bottom_sheet_reservation_totalPrice1.text =
                        reservationAllData[position].payment!!.total_price + "???"
                    bottom_sheet_reservation_totalPrice2.text =
                        reservationAllData[position].payment!!.total_price + "???"
                    bottom_sheet_reservation_mileage.text =
                        (reservationAllData[position].payment!!.real_total_price.toInt() - reservationAllData[position].payment!!.total_price.toInt()).toString() + "P"
                    bottom_sheet_reservation_realTotalPrice.text =
                        reservationAllData[position].payment!!.real_total_price + "???"

                    act_reservation_slidepannel.panelState =
                        SlidingUpPanelLayout.PanelState.ANCHORED
                }
            }
        )

        act_reservation_recyclerview.adapter = reservationAdapter
        act_reservation_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun getData() {

        RequestToServer.service.requestReserveInfo(
            JsonParser.parseString(
                JSONObject().put(
                    "userid",
                    userEmail
                ).toString()
            ) as JsonObject
        ).enqueue(object : Callback<ArrayList<ReserveResponse>> {
            override fun onResponse(
                call: Call<ArrayList<ReserveResponse>>,
                response: Response<ArrayList<ReserveResponse>>
            ) {
                if (response.isSuccessful) {

                    for (item in response.body()!!) {
                        reservationDatas.apply {
                            add(
                                ReservationData(
                                    dateTime = item.date,
                                    headCount = item.headCount,
                                    name = item.name,
                                    location = item.address,
                                    type = item.type
                                )
                            )
                        }

                        reservationAllData.apply {
                            add(
                                ReserveResponse(
                                    _id = item._id,
                                    date = item.date,
                                    headCount = item.headCount,
                                    name = item.name,
                                    type = item.type,
                                    address = item.address,
                                    user = item.user,
                                    payment = item.payment,
                                    finalmile = item.finalmile,
                                    state = item.state
                                )
                            )
                        }
                    }

                    reservationAdapter.datas = reservationDatas
                    reservationAdapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<ArrayList<ReserveResponse>>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }

        })


    }
}