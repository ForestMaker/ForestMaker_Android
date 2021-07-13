package com.example.forestmaker.ui.reserve

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.forestmaker.R
import com.example.forestmaker.ui.reserve.Experience.SelectExperienceActivity
import com.example.forestmaker.ui.reserve.Reservation.ReservationInfoActivity
import kotlinx.android.synthetic.main.fragment_reserve.*
import java.util.*


class ReserveFragment : Fragment() {

    lateinit var reserveBannerAdapter: ReserveBannerAdapter
    val handler = Handler()

    companion object {
        const val NICKNAME = "nickname"
        const val EMAIL = "email"
        fun newInstance(nickname: String, email: String) = ReserveFragment().apply {
            arguments = Bundle().apply {
                putString(EMAIL, email)
                putString(NICKNAME, nickname)
            }
        }
    }

    val user_email by lazy { requireArguments().getString(EMAIL) }
    val user_nickname by lazy { requireArguments().getString(NICKNAME) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reserve, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 사용자 이름 설정
        frag_reserve_txt_name.text = user_nickname
        setAdapter()

        frag_reserve_btn_planting.setOnClickListener {
            val intent = Intent(activity, SelectLocationActivity::class.java)
            intent.putExtra("title", 1)
            intent.putExtra("user_email", user_email)
            startActivity(intent)
        }

        frag_reserve_btn_experience.setOnClickListener {
//            val intent = Intent(activity, SelectLocationActivity::class.java)
//            intent.putExtra("title", 2)
//            startActivity(intent)
            val intent = Intent(activity, SelectExperienceActivity::class.java)
            intent.putExtra("user_email", user_email)
            startActivity(intent)
        }

        frag_reserve_btn_reservationInfo.setOnClickListener {
            val intent = Intent(activity, ReservationInfoActivity::class.java)
            intent.putExtra("user_email", user_email)
            startActivity(intent)
        }


//        handler.postDelayed(runnableCode, 1000)
    }


//    //계속 돌아가는 코드, 자동으로 recyclerview 넘기기.
//    val runnableCode =
//        Runnable {
//            setAdapter()
//            val random = Random().nextInt(5)
////            frag_reserve_newForest_recyclerview.smoothScrollToPosition(random)
//            frag_reserve_newForest_recyclerview.scrollToPosition(random)
//            onResume()
//        }

    //onResume을 통해 runnableCode반복 수행
    override fun onResume() {
        super.onResume()
//        handler.removeCallbacks(runnableCode)
//        handler.postDelayed(runnableCode,4000)
    }

    fun setAdapter() {
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

    }


}