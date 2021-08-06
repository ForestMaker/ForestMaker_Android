package com.forest.forestmaker.ui.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.forest.forestmaker.R
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_badge.*
import kotlinx.android.synthetic.main.activity_reservation_info.*
import kotlinx.android.synthetic.main.bottom_sheet_badge.*

class BadgeActivity : AppCompatActivity(), View.OnClickListener {

    val stringData = arrayListOf<Int>(
        -1,
        R.string.badge1,
        R.string.badge2,
        R.string.badge3,
        R.string.badge4,
        R.string.badge5,
        R.string.badge6,
        R.string.badge7
    )

    val imageData = arrayListOf<Int>(
        -1,
        R.drawable.ic_badge_active_1,
        R.drawable.ic_badge_active_2,
        R.drawable.ic_badge_active_3,
        R.drawable.ic_badge_active_4,
        R.drawable.ic_badge_active_5,
        R.drawable.ic_badge_active_6,
        R.drawable.ic_badge_active_7
    )

    val titleData = arrayListOf<String>(
        "",
        "첫 식목",
        "10그루",
        "세계 환경의 날",
        "식목일",
        "첫 나무 공방 체험",
        "단골손님",
        "전국 8도"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badge)

    }

    private fun setBottomSheet(n: Int) {
        bottom_sheet_badge_image.setImageResource(imageData[n])
        bottom_sheet_badge_title.text = titleData[n]
        bottom_sheet_badge_info.text = getString(stringData[n])

        act_badge_slidepannel.panelState = SlidingUpPanelLayout.PanelState.ANCHORED

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.act_badge_1 -> setBottomSheet(1)
            R.id.act_badge_2 -> setBottomSheet(2)
            R.id.act_badge_3 -> setBottomSheet(3)
            R.id.act_badge_4 -> setBottomSheet(4)
            R.id.act_badge_5 -> setBottomSheet(5)
            R.id.act_badge_6 -> setBottomSheet(6)
            R.id.act_badge_7 -> setBottomSheet(7)

            R.id.act_badge_btn_back -> finish()
        }
    }
}