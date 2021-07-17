package com.forest.forestmaker.server.data

data class MyTreeListResponse(
    val _id: String,
    val userid: String,
    val photo: String,
    val kind: String,
    val addr: String,
    val date: String,
    var contents: String
)
