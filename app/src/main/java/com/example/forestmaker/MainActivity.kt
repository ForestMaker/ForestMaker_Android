package com.example.forestmaker

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.forestmaker.ui.BottomTab.BottomTabAdapter
import com.example.forestmaker.ui.Home.HomeFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id = intent.getStringExtra("id").toString()

        setAdapter()
        setTabBar()

    }

    private fun setTabBar() {
        val bottomTabBar: View = LayoutInflater.from(this).inflate(R.layout.item_tab_layout, null)
        act_main_tabLayout.run {

            addTab(
                    this.newTab()
                            .setCustomView(bottomTabBar.findViewById(R.id.cl_tab_home) as ConstraintLayout)
            )
            addTab(
                    this.newTab()
                            .setCustomView(bottomTabBar.findViewById(R.id.cl_tab_reserve) as ConstraintLayout)
            )
            addTab(
                    this.newTab()
                            .setCustomView(bottomTabBar.findViewById(R.id.cl_tab_mypage) as ConstraintLayout)
            )

            // 인디케이터 없애기
            setSelectedTabIndicator(0)
        }
    }

    private fun setAdapter() {
        act_main_viewPager.adapter =
            BottomTabAdapter(
                supportFragmentManager,
                3,
                id
            )

        act_main_viewPager.offscreenPageLimit = 2
        act_main_viewPager.currentItem = 0

        act_main_viewPager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(
                        act_main_tabLayout
                )
        )

        act_main_tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                act_main_viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

    }
}