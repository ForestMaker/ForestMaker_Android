package com.example.forestmaker.server.data

data class SignUpResponse (
    val message: String,
    val data: SignUpData
)

data class SignUpData(
    val mileage: Int,
    val treecnt: Int,
    val _id: String,
    val id: String,
    val pw: String,
    val nickname: String,
    val __v: Int
)