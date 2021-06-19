package com.example.forestmaker.ui.Reserve.Reservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.ReservationData
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_reservation_info.*

class ReservationInfoActivity : AppCompatActivity() {

    var reservationDatas = mutableListOf<ReservationData>()
    lateinit var reservationAdapter: ReservationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_info)

        act_reservation_btn_back.setOnClickListener {
            finish()
        }

        reservationAdapter = ReservationAdapter(
            this,
            object :ReservationViewHolder.OnClickListener{
                override fun ReservationDetailClick(position: Int) {
                    act_reservation_slidepannel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
                }
            }
        )

        act_reservation_recyclerview.adapter = reservationAdapter
        act_reservation_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        loadData()
    }

    fun loadData(){
        reservationDatas.apply {
            add(
                ReservationData(
                    "2021.06.03 18:00~19:00",
                    "3명",
                    "마곡나루역 근처 나무1",
                    "주소주소 - 1423"
                )
            )
            add(
                ReservationData(
                    "2021.06.03 18:00~19:00",
                    "3명",
                    "마곡나루역 근처 나무2",
                    "주소주소 - 1423"
                )
            )
            add(
                ReservationData(
                    "2021.06.03 18:00~19:00",
                    "3명",
                    "마곡나루역 근처 나무3",
                    "주소주소 - 1423"
                )
            )
            add(
                ReservationData(
                    "2021.06.03 18:00~19:00",
                    "3명",
                    "마곡나루역 근처 나무4",
                    "주소주소 - 1423"
                )
            )
            add(
                ReservationData(
                    "2021.06.03 18:00~19:00",
                    "3명",
                    "마곡나루역 근처 나무5",
                    "주소주소 - 1423"
                )
            )
            add(
                ReservationData(
                    "2021.06.03 18:00~19:00",
                    "3명",
                    "마곡나루역 근처 나무",
                    "주소주소 - 1423"
                )
            )
            add(
                ReservationData(
                    "2021.06.03 18:00~19:00",
                    "3명",
                    "마곡나루역 근처 나무",
                    "주소주소 - 1423"
                )
            )
            add(
                ReservationData(
                    "2021.06.03 18:00~19:00",
                    "3명",
                    "마곡나루역 근처 나무",
                    "주소주소 - 1423"
                )
            )
            add(
                ReservationData(
                    "2021.06.03 18:00~19:00",
                    "3명",
                    "마곡나루역 근처 나무",
                    "주소주소 - 1423"
                )
            )
            add(
                ReservationData(
                    "2021.06.03 18:00~19:00",
                    "3명",
                    "마곡나루역 근처 나무",
                    "주소주소 - 1423"
                )
            )
            add(
                ReservationData(
                    "2021.06.03 18:00~19:00",
                    "3명",
                    "마곡나루역 근처 나무",
                    "주소주소 - 1423"
                )
            )
        }

        reservationAdapter.datas = reservationDatas
        reservationAdapter.notifyDataSetChanged()
    }
}