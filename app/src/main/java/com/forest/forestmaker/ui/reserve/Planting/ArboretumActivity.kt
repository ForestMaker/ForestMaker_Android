package com.forest.forestmaker.ui.reserve.Planting

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.forest.forestmaker.R
import com.forest.forestmaker.data.ShoppingCartData
import kotlinx.android.synthetic.main.activity_arboretum.*

class ArboretumActivity : AppCompatActivity(){

    val tree = arrayOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false)
    var shoppingCartData = ArrayList<ShoppingCartData>()
    var type = ""
    var name = ""
    var address = ""
    var user_email = ""

    private val finishedReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            this@ArboretumActivity.finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arboretum)
        registerFinishedReceiver()

        user_email = intent.getStringExtra("user_email").toString()

        shoppingCartData = intent.getParcelableArrayListExtra<ShoppingCartData>("shoppingCartList") as ArrayList<ShoppingCartData>
        type = intent.getStringExtra("type").toString()
        name = intent.getStringExtra("name").toString()
        address = intent.getStringExtra("address").toString()

        act_arboretum_txt.text = "나무 심을 곳을 총 " + shoppingCartData.size + "개 선택해주세요."

        treeInitail()

        act_arboretum_btn_back.setOnClickListener { finish() }

        act_arboretum_btn_ok.setOnClickListener {
            if (act_arboretum_btn_ok.isSelected) {
                val intent = Intent(this, SelectPlantingDateActivity::class.java)
                intent.putExtra("shoppingCartList", shoppingCartData)
                intent.putExtra("type", type)
                intent.putExtra("address", address)
                intent.putExtra("name", name)
                intent.putExtra("user_email", user_email)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "나무 심을 위치를 골라주세요.", Toast.LENGTH_SHORT).show()
            }
        }


        act_arboretum_tree1.setOnClickListener {
            tree[1] = !tree[1]
            act_arboretum_tree1.isSelected = !act_arboretum_tree1.isSelected
            checkClickedNum()
        }

        act_arboretum_tree2.setOnClickListener {
            tree[2] = !tree[2]
            act_arboretum_tree2.isSelected = !act_arboretum_tree2.isSelected
            checkClickedNum()
        }

        act_arboretum_tree3.setOnClickListener {
            tree[3] = !tree[3]
            act_arboretum_tree3.isSelected = !act_arboretum_tree3.isSelected
            checkClickedNum()
        }

        act_arboretum_tree4.setOnClickListener {
            tree[4] = !tree[4]
            act_arboretum_tree4.isSelected = !act_arboretum_tree4.isSelected
            checkClickedNum()
        }

        act_arboretum_tree5.setOnClickListener {
            tree[5] = !tree[5]
            act_arboretum_tree5.isSelected = !act_arboretum_tree5.isSelected
            checkClickedNum()
        }

        act_arboretum_tree6.setOnClickListener {
            tree[6] = !tree[6]
            act_arboretum_tree6.isSelected = !act_arboretum_tree6.isSelected
            checkClickedNum()
        }

        act_arboretum_tree7.setOnClickListener {
            tree[7] = !tree[7]
            act_arboretum_tree7.isSelected = !act_arboretum_tree7.isSelected
            checkClickedNum()
        }

        act_arboretum_tree8.setOnClickListener {
            tree[8] = !tree[8]
            act_arboretum_tree8.isSelected = !act_arboretum_tree8.isSelected
            checkClickedNum()
        }

        act_arboretum_tree9.setOnClickListener {
            tree[9] = !tree[9]
            act_arboretum_tree9.isSelected = !act_arboretum_tree9.isSelected
            checkClickedNum()
        }

        act_arboretum_tree10.setOnClickListener {
            tree[10] = !tree[10]
            act_arboretum_tree10.isSelected = !act_arboretum_tree10.isSelected
            checkClickedNum()
        }

        act_arboretum_tree11.setOnClickListener {
            tree[11] = !tree[11]
            act_arboretum_tree11.isSelected = !act_arboretum_tree11.isSelected
            checkClickedNum()
        }

        act_arboretum_tree12.setOnClickListener {
            tree[12] = !tree[12]
            act_arboretum_tree12.isSelected = !act_arboretum_tree12.isSelected
            checkClickedNum()
        }

        act_arboretum_tree13.setOnClickListener {
            tree[13] = !tree[13]
            act_arboretum_tree13.isSelected = !act_arboretum_tree13.isSelected
            checkClickedNum()
        }

        act_arboretum_tree14.setOnClickListener {
            tree[14] = !tree[14]
            act_arboretum_tree14.isSelected = !act_arboretum_tree14.isSelected
            checkClickedNum()
        }

        act_arboretum_tree15.setOnClickListener {
            tree[15] = !tree[15]
            act_arboretum_tree15.isSelected = !act_arboretum_tree15.isSelected
            checkClickedNum()
        }

        act_arboretum_tree16.setOnClickListener {
            tree[16] = !tree[16]
            act_arboretum_tree16.isSelected = !act_arboretum_tree16.isSelected
            checkClickedNum()
        }

        act_arboretum_tree17.setOnClickListener {
            tree[17] = !tree[17]
            act_arboretum_tree17.isSelected = !act_arboretum_tree17.isSelected
            checkClickedNum()
        }

        act_arboretum_tree18.setOnClickListener {
            tree[18] = !tree[18]
            act_arboretum_tree18.isSelected = !act_arboretum_tree18.isSelected
            checkClickedNum()
        }

        act_arboretum_tree19.setOnClickListener {
            tree[19] = !tree[19]
            act_arboretum_tree19.isSelected = !act_arboretum_tree19.isSelected
            checkClickedNum()
        }

        act_arboretum_tree20.setOnClickListener {
            tree[20] = !tree[20]
            act_arboretum_tree20.isSelected = !act_arboretum_tree20.isSelected
            checkClickedNum()
        }

        act_arboretum_tree21.setOnClickListener {
            tree[21] = !tree[21]
            act_arboretum_tree21.isSelected = !act_arboretum_tree21.isSelected
            checkClickedNum()
        }

        act_arboretum_tree22.setOnClickListener {
            tree[22] = !tree[22]
            act_arboretum_tree22.isSelected = !act_arboretum_tree22.isSelected
            checkClickedNum()
        }

        act_arboretum_tree23.setOnClickListener {
            tree[23] = !tree[23]
            act_arboretum_tree23.isSelected = !act_arboretum_tree23.isSelected
            checkClickedNum()
        }

        act_arboretum_tree24.setOnClickListener {
            tree[24] = !tree[24]
            act_arboretum_tree24.isSelected = !act_arboretum_tree24.isSelected
            checkClickedNum()
        }

    }
    fun checkClickedNum() {
        var cnt = 0
        for (i in tree) {
            if (i) {
                cnt += 1
            }
        }

        if (cnt == shoppingCartData.size) {
            act_arboretum_btn_ok.isSelected = true
        }
    }

    private fun treeInitail() {
        act_arboretum_tree1.isSelected = false
        act_arboretum_tree2.isSelected = false
        act_arboretum_tree3.isSelected = false
        act_arboretum_tree4.isSelected = false
        act_arboretum_tree5.isSelected = false
        act_arboretum_tree6.isSelected = false
        act_arboretum_tree7.isSelected = false
        act_arboretum_tree8.isSelected = false
        act_arboretum_tree9.isSelected = false
        act_arboretum_tree10.isSelected = false
        act_arboretum_tree11.isSelected = false
        act_arboretum_tree12.isSelected = false
        act_arboretum_tree13.isSelected = false
        act_arboretum_tree14.isSelected = false
        act_arboretum_tree15.isSelected = false
        act_arboretum_tree16.isSelected = false
        act_arboretum_tree17.isSelected = false
        act_arboretum_tree18.isSelected = false
        act_arboretum_tree19.isSelected = false
        act_arboretum_tree20.isSelected = false
        act_arboretum_tree21.isSelected = false
        act_arboretum_tree22.isSelected = false
        act_arboretum_tree23.isSelected = false
        act_arboretum_tree24.isSelected = false
    }

    fun registerFinishedReceiver() {
        Log.e("ArboretumActivity Receiver", "ArboretumActivity")
        val filter = IntentFilter("com.example.forestmaker.ui.reserve.ArboretumActivity.FINISH")
        registerReceiver(finishedReceiver, filter)
    }

    fun unregisterFinishedReceiver() {
        unregisterReceiver(finishedReceiver)
    }


    override fun onDestroy() {
        unregisterFinishedReceiver()
        super.onDestroy()
    }
}