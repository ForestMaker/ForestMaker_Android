package com.forest.forestmaker.ui.mypage

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.forest.forestmaker.R
import com.forest.forestmaker.server.RequestToServer
import com.forest.forestmaker.server.data.MyBadgeResponse
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_badge.*
import kotlinx.android.synthetic.main.bottom_sheet_badge.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        R.drawable.ic_badge_inactive_1,
        R.drawable.ic_badge_inactive_2,
        R.drawable.ic_badge_inactive_3,
        R.drawable.ic_badge_inactive_4,
        R.drawable.ic_badge_inactive_5,
        R.drawable.ic_badge_inactive_6,
        R.drawable.ic_badge_inactive_7
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

    var id = ""
    var treecnt = 0

    val badge1 : ImageView by lazy {
        findViewById<ImageView>(R.id.act_badge_1_image)
    }
    val badge2 : ImageView by lazy {
        findViewById<ImageView>(R.id.act_badge_2_image)
    }
    val badge5 : ImageView by lazy {
        findViewById<ImageView>(R.id.act_badge_5_image)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_badge)

        initailData()
        getData()

    }

    private fun initailData() {
        id = intent.getStringExtra("id").toString()
        treecnt = intent.getIntExtra("treecnt", 0)
    }

    private fun getData() {


        RequestToServer.service.requestMyBadge(JsonParser.parseString(JSONObject().put("id", id).put("treecnt", treecnt).toString()) as JsonObject).enqueue(
            object : Callback<MyBadgeResponse>{
                override fun onResponse(call: Call<MyBadgeResponse>, response: Response<MyBadgeResponse>) {
                    if (response.isSuccessful) {
                        Log.e("Success", response.body().toString())

                        if (response.body()?.n1 == 1) {
                            imageData[1] = R.drawable.ic_badge_active_1
                            badge1.setImageResource(R.drawable.ic_badge_active_1)
                        } else {
                            imageData[1] = R.drawable.ic_badge_inactive_1
                            badge1.setImageResource(R.drawable.ic_badge_inactive_1)
                        }

                        if (response.body()?.n2 == 1) {
                            imageData[2] = R.drawable.ic_badge_active_2
                            badge2.setImageResource(R.drawable.ic_badge_active_2)
                        } else {
                            imageData[2] = R.drawable.ic_badge_inactive_2
                            badge2.setImageResource(R.drawable.ic_badge_inactive_2)
                        }

                        if (response.body()?.n5 == 1) {
                            imageData[5] = R.drawable.ic_badge_active_5
                            badge5.setImageResource(R.drawable.ic_badge_active_5)
                        } else {
                            imageData[5] = R.drawable.ic_badge_inactive_5
                            badge5.setImageResource(R.drawable.ic_badge_inactive_5)
                        }

                    }
                }

                override fun onFailure(call: Call<MyBadgeResponse>, t: Throwable) {
                    Log.e("Fail", t.message.toString())
                }

            }
        )
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