package com.example.forestmaker.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShoppingCartData(
    val itemName: String,
    val itemPrice: String,
    val itemNumber: String
): Parcelable