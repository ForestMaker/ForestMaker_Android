package com.example.forestmaker.ui.Reserve

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forestmaker.R
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
            startActivity(intent)
        }
    }

}