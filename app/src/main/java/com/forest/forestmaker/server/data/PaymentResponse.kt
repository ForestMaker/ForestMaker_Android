package com.forest.forestmaker.server.data

data class PaymentResponse(

    val date: String,
    val headCount: String,
    val name: String,
    val type: String,
    val address: String,
    val user: user,
    val payment: payment,
    val finalmile: Int
)

data class user(
    val user_name: String,
    val user_phone: String,
    val user_id: String
)

data class payment(
    val item: String,
    val item_count: Int,
    val total_count: Int,
    val total_price: String,
    val use_mileage: Int,
    val real_total_price: String
)
