package com.example.forestmaker.ui.mypage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forestmaker.R
import com.example.forestmaker.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_my_page.*

class MyPageFragment : Fragment() {

    companion object {
        const val NICKNAME = "nickname"
        const val EMAIL = "email"
        fun newInstance(nickname: String, email: String) = MyPageFragment().apply {
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
        return inflater.inflate(R.layout.fragment_my_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set initial data
        frag_mypage_txt_userNickname.text = user_nickname
        frag_mypage_txt_email.text = user_email

        // 서버 통신
//        getData(receiveData)

        frag_mypage_btn_logout.setOnClickListener {
            activity?.finish()
        }

        frag_mypage_btn_editProfile.setOnClickListener {
            val intent = Intent(activity, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }

//    private fun getData(id: String) {
//        RequestToServer.service.requestMain(id).enqueue(object : Callback<MainResponse> {
//            override fun onResponse(call: Call<MainResponse>, response: Response<MainResponse>) {
//                if (response.isSuccessful) {
//                    Log.d("success", response.body().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
//                Log.e("error", t.message.toString())
//            }
//
//        })
//    }
}