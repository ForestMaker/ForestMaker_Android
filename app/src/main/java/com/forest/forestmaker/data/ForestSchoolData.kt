package com.forest.forestmaker.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForestSchoolData (
    val name: String,
    val location: String,
    val img: String,
    val contents: String
    ): Parcelable

// dummy data set