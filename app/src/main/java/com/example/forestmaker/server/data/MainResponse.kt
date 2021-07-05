package com.example.forestmaker.server.data

data class MainResponse(
    val message: String,
    val data: UserData,
    val congestion: Int
)

data class UserData(
    val mileage: Int,
    val treecnt: Int,
    val id: String,
    val nickname: String
    )