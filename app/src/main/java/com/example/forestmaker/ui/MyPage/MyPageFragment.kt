package com.example.forestmaker.ui.MyPage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.forestmaker.R
import com.example.forestmaker.ui.Home.HomeFragment
import kotlinx.android.synthetic.main.fragment_my_page.*

class MyPageFragment : Fragment() {

    companion object {
        const val KEY = "key"
        fun newInstance(data: String) = MyPageFragment().apply {
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
        return inflater.inflate(R.layout.fragment_my_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // set initial data
        frag_mypage_txt_userNickname.text = receiveData

        frag_mypage_btn_editProfile.setOnClickListener {
            val intent = Intent(activity, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }
}