package com.example.forestmaker.ui.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forestmaker.R
import com.example.forestmaker.server.RequestToServer
import com.example.forestmaker.server.data.MyPageResponse
import com.example.forestmaker.ui.home.HomeFragment
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.fragment_my_page.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    var pw = ""

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
        user_email?.let { getData(it) }

        frag_mypage_btn_logout.setOnClickListener {
            activity?.finish()
        }

        frag_mypage_btn_editProfile.setOnClickListener {
            val intent = Intent(activity, EditProfileActivity::class.java)
            intent.putExtra("user_id", user_email)
            intent.putExtra("user_nickname", frag_mypage_txt_userNickname.text.toString())
            intent.putExtra("user_pw", pw)
            startActivity(intent)
        }
    }

    private fun getData(user_email: String) {
        RequestToServer.service.requestMyInfo(JsonParser.parseString(JSONObject().put("id", user_email).toString()) as JsonObject).enqueue(object : Callback<MyPageResponse> {
            override fun onResponse(call: Call<MyPageResponse>, response: Response<MyPageResponse>) {
                if (response.isSuccessful) {
                    Log.d("success mypage", response.body().toString())
                    frag_mypage_txt_phone.text = response.body()?.phone
                    frag_mypage_txt_userNickname.text = response.body()?.nickname
                    frag_mypage_txt_email.text = response.body()?.id
                    frag_mypage_txt_nickname.text = response.body()?.nickname
                    pw = response.body()?.pw.toString()
                }
            }

            override fun onFailure(call: Call<MyPageResponse>, t: Throwable) {
                Log.e("error mypage", t.message.toString())
            }

        })
    }

    override fun onResume() {
        super.onResume()
        user_email?.let { getData(it) }
    }
}