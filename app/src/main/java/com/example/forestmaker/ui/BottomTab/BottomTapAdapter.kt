package com.example.forestmaker.ui.BottomTab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.forestmaker.ui.Home.HomeFragment
import com.example.forestmaker.ui.MyPage.MyPageFragment
import com.example.forestmaker.ui.Reserve.ReserveFragment

class BottomTabAdapter(fm: FragmentManager, private val fragmentCount: Int) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return HomeFragment()
            1 -> return ReserveFragment()
            2 -> return MyPageFragment()
            else -> null!!
        }
    }

    override fun getCount(): Int = fragmentCount

}