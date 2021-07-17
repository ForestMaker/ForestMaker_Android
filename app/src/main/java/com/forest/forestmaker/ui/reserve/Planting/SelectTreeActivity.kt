package com.forest.forestmaker.ui.reserve.Planting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.forest.forestmaker.R
import com.forest.forestmaker.data.SelectTreeData
import com.forest.forestmaker.data.ShoppingCartData
import com.forest.forestmaker.server.RequestToServer
import com.forest.forestmaker.server.data.StoreItem
import com.forest.forestmaker.ui.reserve.ShoppingCartAdapter
import com.forest.forestmaker.ui.reserve.ShoppingCartViewHolder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_select_tree.*
import org.json.JSONObject
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
    var location_trees = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_tree)

        location_trees = intent.getStringExtra("location_trees").toString()
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

        RequestToServer.service.requestLocationTrees(JsonParser.parseString(JSONObject().put("trees", location_trees).toString()) as JsonObject).enqueue(object :Callback<ArrayList<StoreItem>> {
            override fun onResponse(
                call: Call<ArrayList<StoreItem>>,
                response: Response<ArrayList<StoreItem>>
            ) {
                if (response.isSuccessful) {
                    Log.e("success to get location trees", response.body().toString())
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




}