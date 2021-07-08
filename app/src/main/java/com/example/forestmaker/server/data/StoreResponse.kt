package com.example.forestmaker.server.data

data class StoreResponse(
    val message: String,
    val data: ArrayList<StoreItem>
)

data class StoreItem(
    val _id: String,
    val id: Int,
    val type: String,
    val item_name: String,
    val item_img: String,
    val item_price_str: String,
    val item_price_int: Int,
    var item_number: Int
)


//id: 1
//type: "tree_data"
//item_name: "가시나무"
//item_img:"https://postfiles.pstatic.net/MjAyMTA3MDlfMTg5/MDAxNjI1NzU4MTA4Nzgx.MC..."
//item_price_str:"1,000원"
//item_price_int:1000
//item_number:0


//"item_price_int": 2000,
//"item_number": 0,
//"_id": "60e744430963eccd758efe3b",
//"id": 101,
//"type": "tonic_data",
//"item_name": "식물 영양제 비료 액비",
//"item_img": "https://postfiles.pstatic.net/MjAyMTA3MDlfMTEy/MDAxNjI1NzU5MTU5Nzc5.1DNH-G5qgVKcUkma5F0onF2JuW15yCePHa6DxSUAZXog.vCfPPJjKBrgg_DL676JpI6fVd2TDFoW-wf5vfl2ZATIg.JPEG.jhk8806/%EC%98%81%EC%96%91%EC%A0%9C1.jpg?type=w966",
//"item_price_str": "2,000원"