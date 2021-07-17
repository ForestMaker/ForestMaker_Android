package com.forest.forestmaker.server.data

data class SignInResponse(
    val message: String,
    val data: SignInData
)

data class SignInData(
    val mileage: Int,
    val treecnt: Int,
    val _id: String,
    val id: String,
    val pw: String,
    val phone: String,
    val nickname: String,
    val __v: Int
)