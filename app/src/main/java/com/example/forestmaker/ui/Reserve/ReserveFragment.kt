package com.example.forestmaker.ui.Reserve

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forestmaker.R
import com.example.forestmaker.ui.Reserve.Reservation.ReservationInfoActivity
import kotlinx.android.synthetic.main.fragment_reserve.*

class ReserveFragment : Fragment() {

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
    }

}