package com.forest.forestmaker.ui.reserve

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.forest.forestmaker.R
import com.forest.forestmaker.ui.reserve.Experience.SelectExperienceActivity
import com.forest.forestmaker.ui.reserve.Reservation.ReservationInfoActivity
import kotlinx.android.synthetic.main.fragment_reserve.*


class ReserveFragment : Fragment() {

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

    lateinit var reserveBannerAdapter: ReserveBannerAdapter
    private val userEmail by lazy { requireArguments().getString(EMAIL) }
    private val userNickname by lazy { requireArguments().getString(NICKNAME) }

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
        frag_reserve_txt_name.text = userNickname

        setAdapter()
        setButton()

    }

    private fun setButton() {
        frag_reserve_btn_planting.setOnClickListener {
            val intent = Intent(activity, SelectLocationActivity::class.java)
            intent.putExtra("title", 1)
            intent.putExtra("user_email", userEmail)
            startActivity(intent)
        }

        frag_reserve_btn_experience.setOnClickListener {
            val intent = Intent(activity, SelectExperienceActivity::class.java)
            intent.putExtra("user_email", userEmail)
            startActivity(intent)
        }

        frag_reserve_btn_reservationInfo.setOnClickListener {
            val intent = Intent(activity, ReservationInfoActivity::class.java)
            intent.putExtra("user_nickname", userNickname)
            intent.putExtra("user_email", userEmail)
            startActivity(intent)
        }
    }

    private fun setAdapter() {
        reserveBannerAdapter = activity?.let {
            ReserveBannerAdapter(it, object : ReserveBannerViewHolder.OnClickListener{
                override fun onClickBanner(position: Int) {

                }
            })
        }!!

        frag_reserve_newForest_recyclerview.adapter = reserveBannerAdapter
        frag_reserve_newForest_recyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        val snapHelperBanner = PagerSnapHelper()
        snapHelperBanner.attachToRecyclerView(frag_reserve_newForest_recyclerview)

    }
}