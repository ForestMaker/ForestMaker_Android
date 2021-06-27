package com.example.forestmaker.ui.Home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.forestmaker.R
import com.example.forestmaker.data.BannerData
import com.example.forestmaker.server.RequestToServer
import com.example.forestmaker.server.data.MainResponse
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    val homeBannerDatas = mutableListOf<BannerData>()
    lateinit var homeBannerAdapter: HomeBannerAdapter

    companion object {
        const val KEY = "key"
        fun newInstance(data: String) = HomeFragment().apply {
            arguments = Bundle().apply {
                putString(KEY, data)
            }
        }
    }

    val receiveData by lazy { requireArguments().getString(KEY) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set initial data
        frag_home_txt_userName.text = receiveData


        // 서버 통신
//        getData(receiveData)

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

    private fun getData(id: String) {
        RequestToServer.service.requestMain(id).enqueue(object : Callback<MainResponse> {
            override fun onResponse(call: Call<MainResponse>, response: Response<MainResponse>) {
                if (response.isSuccessful) {
                    Log.d("success", response.body().toString())
                }
            }

            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
                Log.e("error", t.message.toString())
            }

        })
    }
}