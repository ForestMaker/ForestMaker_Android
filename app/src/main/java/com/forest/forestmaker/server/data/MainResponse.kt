package com.forest.forestmaker.server.data

data class MainResponse(
    val main: UserData,
    val forest: ArrayList<String>
)

data class UserData(
    val _id: String,
    val mileage: Int,
    val treecnt: Int,
    val id: String,
    val phone: String,
    val nickname: String
    )