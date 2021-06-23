package com.example.forestmaker.ui.Reserve

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.forestmaker.R
import com.example.forestmaker.data.BannerData
import com.example.forestmaker.ui.Reserve.Reservation.ReservationInfoActivity
import kotlinx.android.synthetic.main.fragment_reserve.*

class ReserveFragment : Fragment() {


    lateinit var reserveBannerAdapter: ReserveBannerAdapter
    val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reserve, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        frag_reserve_btn_planting.setOnClickListener {
            val intent = Intent(activity, SelectLocationActivity::class.java)
            intent.putExtra("title", 1)
            startActivity(intent)
        }

        frag_reserve_btn_experience.setOnClickListener {
            val intent = Intent(activity, SelectLocationActivity::class.java)
            intent.putExtra("title", 2)
            startActivity(intent)
        }

        frag_reserve_btn_reservationInfo.setOnClickListener {
            val intent = Intent(activity, ReservationInfoActivity::class.java)
            startActivity(intent)
        }

        reserveBannerAdapter = activity?.let {
            ReserveBannerAdapter(it, object : ReserveBannerViewHolder.OnClickListener{
                override fun onClickBanner(position: Int) {

                }

            })
        }!!

        frag_reserve_newForest_recyclerview.adapter = reserveBannerAdapter
        frag_reserve_newForest_recyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        val snapHelper_banner = PagerSnapHelper()
        snapHelper_banner.attachToRecyclerView(frag_reserve_newForest_recyclerview)

        handler.postDelayed(runnableCode, 1000)
    }

    var i = 4

    //계속 돌아가는 코드, 자동으로 recyclerview 넘기기.
    val runnableCode =
        Runnable {
            if(i>0) {
                frag_reserve_newForest_recyclerview.smoothScrollToPosition(reserveBannerAdapter.itemCount - i)
                i--
            }else{
                i=4
                frag_reserve_newForest_recyclerview.smoothScrollToPosition(reserveBannerAdapter.itemCount - i)
            }
            onResume()
        }

    //onResume을 통해 runnableCode반복 수행
    override fun onResume() {
        super.onResume()
        handler.removeCallbacks(runnableCode)
        handler.postDelayed(runnableCode,4000)
    }


}