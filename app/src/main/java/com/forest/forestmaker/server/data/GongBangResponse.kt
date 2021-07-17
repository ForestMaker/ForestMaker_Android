package com.forest.forestmaker.server.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GongBangResponse(
    val name : String,
    val description: String,
    val address: String,
    val hours: String,
    val runtime: String,
    val participants: String,
    val fee: String,
    val fee_int: Int,
    val img_list: ArrayList<String>
): Parcelable

@Parcelize
data class img_list(
    val img: String
): Parcelable
