package com.forest.forestmaker.server.data

data class ReserveResponse(
    val _id: String?,
    val date: String?,
    val headCount: String?,
    val name: String?,
    val type: String?,
    val address: String?,
    val user: user?,
    val payment: payment?,
    val finalmile: Int?
)