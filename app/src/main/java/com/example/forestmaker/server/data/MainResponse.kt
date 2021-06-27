package com.example.forestmaker.server.data

data class MainResponse(
    val user_data: UserData,
    val message: String,
    val crowded: Int
)

data class UserData(
    val mileage: Int,
    val treecnt: Int,
    val nickname: String
    )