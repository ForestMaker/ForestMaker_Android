package com.forest.forestmaker.server.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForestSchool(
    val name : String,
    val address: String,
    val hours: String,
    val runtime: String,
    val participants: String,
    val fee: String,
    val fee_int: Int,
    val info: String,
    val image: String
): Parcelable