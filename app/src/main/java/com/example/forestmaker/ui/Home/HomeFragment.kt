package com.example.forestmaker.ui.Home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.forestmaker.R
import com.example.forestmaker.data.BannerData
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    val homeBannerDatas = mutableListOf<BannerData>()
    lateinit var homeBannerAdapter: HomeBannerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeBannerAdapter = HomeBannerAdapter(
            activity,
            object: HomeBannerViewHolder.OnClickListener {
                override fun onBannerClick(position: Int) {

                }
            }
        )

        frag_home_btn_mytree.setOnClickListener {
            val intent = Intent(activity, MyTreeActivity::class.java)
            startActivity(intent)
        }


        frag_home_recyclerview.adapter = homeBannerAdapter
        frag_home_recyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(frag_home_recyclerview)

        loadData()

    }

    fun loadData(){
        homeBannerDatas.apply {
            add(
                BannerData(
                    "https://i.pinimg.com/564x/fd/ce/07/fdce074ecbbde2001aa341b2d8cc40fe.jpg",
                    "뚝딱뚝딱 수저 만들기"
                )
            )

            add(
                BannerData(
                    "https://i.pinimg.com/564x/14/15/38/1415387cba0c6fa8b62281ed62ea4b61.jpg",
                    "나뭇잎 그릇 만들기"
                )
            )

            add(
                BannerData(
                    "https://i.pinimg.com/564x/5f/50/03/5f5003b87d39a576a11e9f17d6547bff.jpg",
                    "친환경 대나무 칫솔"
                )
            )
        }

        homeBannerAdapter.datas = homeBannerDatas
        homeBannerAdapter.notifyDataSetChanged()
    }
}