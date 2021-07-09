package com.example.forestmaker.server.data

data class MyTreeListResponse(
    val message: String,
    val data: ArrayList<MyTree>
)

data class MyTree(
    val _id: String,
    val user: String,
    val tree_img: String,
    val tree_name: String,
    val tree_location: String,
    val tree_date: String,
    var tree_diary: String
)


//"tree_diary": "무럭무럭 자라라~",
//"_id": "60e816130949e20aaed1a531",
//"user": "test",
//"tree_img": "https://postfiles.pstatic.net/MjAyMTA3MDlfMTMx/MDAxNjI1NzU5MDM3NzE2.pLpYEz88SmsfurymDfuSYMiv5NG4cgwE_nhtFP61C-Eg.xekOpiYwcCHUsdOOLFTpYhDf6HGC7tZj-hI07tjeD74g.JPEG.jhk8806/%EC%89%AC%EB%82%98%EB%AC%B4.jpg?type=w966",
//"tree_name": "쉬나무",
//"tree_location": "용인시 식목원",
//"tree_date": "2020.04.02"