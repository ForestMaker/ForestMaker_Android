package com.example.forestmaker.ui.BottomTab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.forestmaker.ui.home.HomeFragment
import com.example.forestmaker.ui.mypage.MyPageFragment
import com.example.forestmaker.ui.reserve.ReserveFragment

class BottomTabAdapter(fm: FragmentManager, private val fragmentCount: Int, private val nickname: String, private val email: String) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return HomeFragment.newInstance(email)
            1 -> return ReserveFragment.newInstance(nickname, email)
            2 -> return MyPageFragment.newInstance(nickname, email)
            else -> null!!
        }
    }

    override fun getCount(): Int = fragmentCount

}