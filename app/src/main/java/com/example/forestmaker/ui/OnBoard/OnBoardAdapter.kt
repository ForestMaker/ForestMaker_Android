package com.example.forestmaker.ui.OnBoard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class OnBoardAdapter(fm: FragmentManager, private val fragmentCount: Int) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return OnBoard1Fragment()
            1 -> return OnBoard2Fragment()
            2 -> return OnBoard3Fragment()
            else -> null!!
        }
    }

    override fun getCount(): Int = fragmentCount

}
