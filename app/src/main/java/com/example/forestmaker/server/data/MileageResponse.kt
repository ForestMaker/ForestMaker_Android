package com.example.forestmaker.server.data

data class MileageResponse(
    val message: String,
    val data: ArrayList<MileageData>
)

data class MileageData(
    val user: String,
    val type: Boolean,
    val mileage: String,
    val date: String,
    val item: String,
    val address: String,
    val headcount: String
)