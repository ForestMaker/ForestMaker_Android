package com.example.forestmaker.ui.OnBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.forestmaker.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_on_board.*


class OnBoardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board)

        // create ViewPager adapter
        val viewPagerAdapter = OnBoardAdapter(supportFragmentManager, 3)

        // Set Adapter for ViewPager
        act_onboard_viewpager.adapter = viewPagerAdapter

        // Setup dot's indicator
        val onBoardingTab: View = LayoutInflater.from(this).inflate(R.layout.view_onboarding_bottom, null)
        act_onboard_tab.run {

            addTab(
                this.newTab()
                    .setCustomView(onBoardingTab.findViewById(R.id.onboarding_indicator_1) as ImageView)
            )
            addTab(
                this.newTab()
                    .setCustomView(onBoardingTab.findViewById(R.id.onboarding_indicator_2) as ImageView)
            )
            addTab(
                this.newTab()
                    .setCustomView(onBoardingTab.findViewById(R.id.onboarding_indicator_3) as ImageView)
            )

            // 인디케이터 없애기
            setSelectedTabIndicator(0)
        }

        act_onboard_viewpager.addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                act_onboard_tab
            )
        )


        // tab indicator click disabled
        val tabStrip =
            act_onboard_tab.getChildAt(0) as LinearLayout
        for (i in 0 until tabStrip.childCount) {
            tabStrip.getChildAt(i).setOnTouchListener { v, event -> true }
        }



    }

}