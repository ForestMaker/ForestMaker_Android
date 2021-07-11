package com.example.forestmaker.server.data

data class MileageResponse(
    val _id: String,
    val userid: String,
    val type: Boolean,
    val mileage: String,
    val date: String,
    val item: String,
    val address: String,
    val headcount: String
)