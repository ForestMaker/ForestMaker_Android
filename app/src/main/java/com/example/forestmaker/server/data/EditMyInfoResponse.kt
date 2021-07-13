package com.example.forestmaker.server.data

data class EditMyInfoResponse(
    val result: result,
    val connection: connection,
    val modifiedCount: Int,
    val upsertedId: Int,
    val upsertedCount: Int,
    val matchedCount: Int,
    val n: Int,
    val nModified: Int,
    val ok: Int
)

data class result(
    val n: Int,
    val nModified: Int,
    val ok: Int
)

data class connection(
    val id: Int,
    val host: String,
    val port: Int
)