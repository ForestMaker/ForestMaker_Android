package com.example.forestmaker.data

data class StoreItemData(
    val  category: String,
    val categoryData: MutableList<storeDatas>
)

data class storeDatas(
    val itemImg: String,
    val itemName: String,
    val itemPrice: String,
    var itemNumber: Int
)
