package com.example.forestmaker.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShoppingCartData(
    val itemImg: String,
    val itemName: String,
    val itemPrice_str: String,
    var itemPrice_int: Int,
    var itemNumber: Int
): Parcelable