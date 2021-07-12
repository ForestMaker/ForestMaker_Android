package com.example.forestmaker.ui.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.forestmaker.R
import com.example.forestmaker.data.BannerData
import com.example.forestmaker.server.data.MainResponse
import com.example.forestmaker.ui.home.mileage.MileageActivity
import com.example.forestmaker.ui.reserve.ReserveFragment
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_my_page.*
import org.json.JSONObject


class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    lateinit var binding: com.example.forestmaker.databinding.FragmentHomeBinding

    val homeBannerDatas = mutableListOf<BannerData>()
    lateinit var homeBannerAdapter: HomeBannerAdapter

    companion object {
        const val NICKNAME = "nickname"
        const val EMAIL = "email"
        fun newInstance(nickname: String, email: String) = HomeFragment().apply {
            arguments = Bundle().apply {
                putString(EMAIL, email)
                putString(NICKNAME, nickname)
            }
        }
    }

    val user_email by lazy { requireArguments().getString(EMAIL) }
    val user_nickname by lazy { requireArguments().getString(NICKNAME) }



    lateinit var body: JsonObject


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val SDK_INT = Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
            //your codes here


            viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
            binding.vm = (this@HomeFragment).viewModel

            homeBannerAdapter = HomeBannerAdapter(activity)

            user_email?.let {
                val idJsonData = JSONObject().put("id", user_email)
                body = JsonParser.parseString(idJsonData.toString()) as JsonObject
                viewModel.getHome(body)
            }

            // Create the observer which updates the UI.
            val main_observer = Observer<MainResponse> { it ->
                // Update the UI, in this case, a TextView.
                frag_home_txt_userName.text = it.main.nickname

                frag_home_text_mileage.text = it.main.mileage.toString() + "P"
                frag_home_text_treecnt.text = "총 " + it.main.treecnt.toString() + "그루 "
                frag_home_progress.progress = it.main.treecnt

                frag_home_text_co2_detail.text =
                    "연 " + (664 * it.main.treecnt).toString() + "대기열 흡수 "
                frag_home_text_dust_detail.text =
                    "연 " + (35.7 * it.main.treecnt).toString() + "미세먼지 저감"
                frag_home_text_o2_detail.text =
                    "연 " + (1799 * it.main.treecnt).toString() + "kg 산소 발생"

//                when (it.congestion) {
//                    1 -> frag_home_img_congestion.setImageResource(R.drawable.icon_crowded_1)
//                    2 -> frag_home_img_congestion.setImageResource(R.drawable.icon_crowded_2)
//                    3 -> frag_home_img_congestion.setImageResource(R.drawable.icon_crowded_3)
//                }
                frag_home_img_congestion.setImageResource(R.drawable.icon_crowded_1)

                for (item in it.forest) {
                    homeBannerDatas.apply {
                        add(
                            BannerData(
                                bannerImg = item
                            )
                        )
                    }
                }

                homeBannerAdapter.datas = homeBannerDatas
                homeBannerAdapter.notifyDataSetChanged()
            }

            // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
            viewModel.homeRes.observe(requireActivity(), main_observer)


            frag_home_btn_mytree.setOnClickListener {
                val intent = Intent(activity, MyTreeActivity::class.java)
                intent.putExtra("user_email", user_email)
                startActivity(intent)
            }

            frag_home_text_mileage.setOnClickListener {
                val intent = Intent(activity, MileageActivity::class.java)
                intent.putExtra("user_email", user_email)
                startActivity(intent)
            }

            frag_home_recyclerview.adapter = homeBannerAdapter
            frag_home_recyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(frag_home_recyclerview)

//            loadData()

            val dialog = activity?.let { Dialog(it) }
            dialog?.setContentView(R.layout.dialog_home)
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val iv: ImageView = dialog?.findViewById(R.id.dialog_home) as ImageView

            frag_home_dialog_dust.setOnClickListener {
                iv.setImageResource(R.drawable.dialog_home_dust)
                dialog.show()
            }

            frag_home_dialog_o2.setOnClickListener {
                iv.setImageResource(R.drawable.dialog_home_o2)
                dialog.show()
            }

            frag_home_dialog_co2.setOnClickListener {
                iv.setImageResource(R.drawable.dialog_home_co2)
                dialog.show()
            }

        }

    }

//    fun loadData(){
//        homeBannerDatas.apply {
//            add(
//                BannerData(
//                    "https://cdn.pixabay.com/photo/2016/06/27/15/28/roe-deer-1482712_1280.jpg",
//                    "우리 숲에 살고 있는 \n 동물과 식물 소개"
//                )
//            )
//            add(
//                BannerData(
//                    "https://cdn.pixabay.com/photo/2017/06/08/17/15/tongyeong-2384216_1280.jpg",
//                    "스트레스 날릴 수 있는 \n 힐링 체험"
//                )
//            )
//            add(
//                BannerData(
//                    "https://cdn.pixabay.com/photo/2017/11/21/09/37/park-2967710_1280.jpg",
//                    "엄마와 아이랑 같이 하는 \n 명상 채험"
//                )
//            )
//            add(
//                BannerData(
//                    "https://cdn.pixabay.com/photo/2017/05/29/11/16/footprint-2353510_1280.jpg",
//                    "맨발로 흙 걷기 체험"
//                )
//            )
//
//            add(
//                BannerData(
//                    "https://cdn.pixabay.com/photo/2020/03/18/23/58/chunnam-4945781_1280.jpg",
//                    "숲 전문가와 함께하는 \n 둘레길 숲 해설"
//                )
//            )
//
//            add(
//                BannerData(
//                    "https://cdn.pixabay.com/photo/2013/02/21/19/12/lumber-84678_1280.jpg",
//                    "피톤치드 뿜뿜 \n 나무 공부하기"
//                )
//            )
//        }
//
//        homeBannerAdapter.datas = homeBannerDatas
//        homeBannerAdapter.notifyDataSetChanged()
//    }

    override fun onResume() {
        super.onResume()

        user_email?.let {
            viewModel.getHome(body)
        }
        Log.e("HomeFrag", "onResume")
    }
}