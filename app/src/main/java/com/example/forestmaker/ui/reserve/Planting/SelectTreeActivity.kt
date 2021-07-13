package com.example.forestmaker.ui.reserve.Planting

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.forestmaker.R
import com.example.forestmaker.data.SelectTreeData
import com.example.forestmaker.data.ShoppingCartData
import com.example.forestmaker.server.RequestToServer
import com.example.forestmaker.server.data.StoreItem
import com.example.forestmaker.ui.reserve.ShoppingCartAdapter
import com.example.forestmaker.ui.reserve.ShoppingCartViewHolder
import kotlinx.android.synthetic.main.activity_select_tree.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectTreeActivity : AppCompatActivity() {

    lateinit var selectTreeAdapter: SelectTreeAdapter
    lateinit var shoppingCartAdapter: ShoppingCartAdapter
    var type = ""
    var name = ""
    var address = ""
    var user_email = ""

    private val finishedReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            this@SelectTreeActivity.finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_tree)
        registerFinishedReceiver()
        user_email = intent.getStringExtra("user_email").toString()
        type = intent.getStringExtra("type").toString()
        name = intent.getStringExtra("name").toString()
        address = intent.getStringExtra("address").toString()

        act_select_tree_btn_back.setOnClickListener { finish() }
        act_select_tree_btn_next.setOnClickListener {
            val intent = Intent(this, ArboretumActivity::class.java)
            intent.putExtra("shoppingCartList", shoppingCartAdapter.datas)
            intent.putExtra("type", type)
            intent.putExtra("address", address)
            intent.putExtra("name", name)
            intent.putExtra("user_email", user_email)
            startActivity(intent)
            finish()
        }
        shoppingCartAdapter = ShoppingCartAdapter(this, object : ShoppingCartViewHolder.onClickListener{
            override fun onClickItemDelete(position: Int) {}
            override fun onPlusItem(position: Int) {}
            override fun onMinusItem(position: Int) {}
        })

        selectTreeAdapter = SelectTreeAdapter(this,
        object : SelectTreeViewHolder.OnClickListener{
            override fun onClickTree(position: Int) {
                //
                shoppingCartAdapter.datas.add(
                    ShoppingCartData(
                        itemImg = selectTreeAdapter.data[position].image,
                        itemName = selectTreeAdapter.data[position].name,
                        itemPrice_int = selectTreeAdapter.data[position].price_int,
                        itemPrice_str = selectTreeAdapter.data[position].price,
                        itemNumber = 1
                    )
                )
                shoppingCartAdapter.notifyDataSetChanged()

                act_select_tree_btn_next.isSelected = shoppingCartAdapter.datas.size > 0
            }

        })


        act_select_tree_recyclerview.adapter = selectTreeAdapter
        act_select_tree_recyclerview.layoutManager = GridLayoutManager(this, 3)

        getTreeData()
    }

    fun getTreeData() {
        RequestToServer.service.requestStoreTree().enqueue(object :Callback<ArrayList<StoreItem>> {
            override fun onResponse(
                call: Call<ArrayList<StoreItem>>,
                response: Response<ArrayList<StoreItem>>
            ) {
                if (response.isSuccessful) {
                    val list = ArrayList<SelectTreeData>()

                    for (item in response.body()!!) {
                        list.apply {
                            add(
                                SelectTreeData(
                                    image = item.photo,
                                    name = item.name,
                                    price = item.price,
                                    price_int = item.price_int
                                )
                            )
                        }
                    }

                    selectTreeAdapter.data = list
                    selectTreeAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<ArrayList<StoreItem>>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }

        })
    }

    fun registerFinishedReceiver() {
        Log.e("SelectTreeActivity Receiver", "SelectTreeActivity")
        val filter = IntentFilter("com.example.forestmaker.ui.reserve.SelectTreeActivity.FINISH")
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