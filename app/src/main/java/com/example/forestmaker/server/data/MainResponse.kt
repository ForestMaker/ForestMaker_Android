package com.example.forestmaker.server.data

data class MainResponse(
    val main: UserData,
    val forest: ArrayList<HomeForest>
)

data class UserData(
    val _id: String,
    val mileage: Int,
    val treecnt: Int,
    val id: String,
    val phone: String,
    val nickname: String
    )

data class HomeForest(

    val _id: String,
    val title:String,
    val addr: String,
    val contents: String,
    val photo: String
)