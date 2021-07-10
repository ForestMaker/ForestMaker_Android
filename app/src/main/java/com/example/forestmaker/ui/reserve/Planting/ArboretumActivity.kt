package com.example.forestmaker.ui.reserve.Planting

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.forestmaker.R
import kotlinx.android.synthetic.main.activity_arboretum.*

class ArboretumActivity : AppCompatActivity(){

    val tree = arrayOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arboretum)

        treeInitail()

        act_arboretum_btn_back.setOnClickListener { finish() }

        act_arboretum_btn_ok.setOnClickListener {
            if (act_arboretum_btn_ok.isSelected) {
                val intent = Intent(this, SelectPlantingDateActivity::class.java)
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
    }
    fun checkClickedNum() {
        var cnt = 0
        for (i in tree) {
            if (i) {
                cnt += 1
            }
        }

        if (cnt == 2) {
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

}