package com.forest.forestmaker.ui.home

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
import com.forest.forestmaker.R
import com.forest.forestmaker.data.BannerData
import com.forest.forestmaker.server.RequestToServer
import com.forest.forestmaker.server.data.GongBangResponse
import com.forest.forestmaker.server.data.MainResponse
import com.forest.forestmaker.ui.home.mileage.MileageActivity
import com.forest.forestmaker.ui.reserve.Experience.gongbang.GongBangActivity
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_my_page.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    lateinit var binding: com.forest.forestmaker.databinding.FragmentHomeBinding

    val homeBannerDatas = mutableListOf<BannerData>()
    lateinit var homeBannerAdapter: HomeBannerAdapter
    var gongbangData = ArrayList<GongBangResponse>()

    private lateinit var dialog: Dialog
    private lateinit var iv: ImageView

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

    val userEmail by lazy { requireArguments().getString(EMAIL) }
    val userNickname by lazy { requireArguments().getString(NICKNAME) }

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

            viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
            binding.vm = (this@HomeFragment).viewModel

            setButton()
            setDialog()
            setAdapter()
            getGongBangData()

            userEmail?.let {
                val idJsonData = JSONObject().put("id", userEmail)
                body = JsonParser.parseString(idJsonData.toString()) as JsonObject
                viewModel.getHome(body)
            }

            // Create the observer which updates the UI.
            val mainObserver = Observer<MainResponse> { it ->
                // Update the UI, in this case, a TextView.
                frag_home_txt_userName.text = it.main.nickname

                frag_home_text_mileage.text = it.main.mileage.toString() + "P"
                frag_home_text_treecnt.text = "총 " + it.main.treecnt.toString() + "그루 "
                frag_home_progress.progress = it.main.treecnt

                frag_home_text_co2_detail.text =
                    "연 " + (664 * it.main.treecnt).toString() + "kcal 대기열 흡수 "
                frag_home_text_dust_detail.text =
                    "연 " + (35.7 * it.main.treecnt).toInt().toString() + "g 미세먼지 저감"
                frag_home_text_o2_detail.text =
                    "연 " + (1799 * it.main.treecnt).toString() + "kg 산소 발생"

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
            viewModel.homeRes.observe(requireActivity(), mainObserver)

        }
    }

    override fun onResume() {
        super.onResume()

        userEmail?.let {
            viewModel.getHome(body)
        }
        Log.e("HomeFrag", "onResume")
    }

    private fun setButton() {
        frag_home_btn_mytree.setOnClickListener {
            val intent = Intent(activity, MyTreeActivity::class.java)
            intent.putExtra("user_email", userEmail)
            startActivity(intent)
        }

        frag_home_text_mileage.setOnClickListener {
            val intent = Intent(activity, MileageActivity::class.java)
            intent.putExtra("user_email", userEmail)
            startActivity(intent)
        }

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

    private fun setDialog() {
        dialog = activity?.let { Dialog(it) }!!
        dialog.setContentView(R.layout.dialog_home)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        iv = dialog.findViewById(R.id.dialog_home) as ImageView
    }

    private fun setAdapter() {
        homeBannerAdapter =
            HomeBannerAdapter(activity, object : HomeBannerViewHolder.OnClickListener {
                override fun onClickBanner(position: Int) {
                    val intent = Intent(activity, GongBangActivity::class.java)
                    intent.putExtra("gongbangList", gongbangData)
                    intent.putExtra("position", position)
                    intent.putExtra("user_email", userEmail)
                    startActivity(intent)
                }

            })

        frag_home_recyclerview.adapter = homeBannerAdapter
        frag_home_recyclerview.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(frag_home_recyclerview)
    }

    private fun getGongBangData() {
        RequestToServer.service.requestGongbang()
            .enqueue(object : Callback<ArrayList<GongBangResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<GongBangResponse>>,
                    response: Response<ArrayList<GongBangResponse>>
                ) {
                    if (response.isSuccessful) {
                        for (item in response.body()!!) {
                            gongbangData.apply {
                                add(
                                    GongBangResponse(
                                        name = item.name,
                                        description = item.description,
                                        address = item.address,
                                        hours = item.hours,
                                        runtime = item.runtime,
                                        participants = item.participants,
                                        fee = item.fee,
                                        fee_int = item.fee_int,
                                        img_list = item.img_list
                                    )
                                )
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<GongBangResponse>>, t: Throwable) {
                    Log.e("fail", t.message.toString())
                }
            })
    }
}