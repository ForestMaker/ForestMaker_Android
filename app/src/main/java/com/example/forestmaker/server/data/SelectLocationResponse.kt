package com.example.forestmaker.server.data

data class SelectLocationResponse(

    val STAGE_NMPL: String, // 단지명(산이름 등)
    val CTRV_NM : String, // 시도명
    val SGMS_NM: String, // 시군구명
    val EMNDN_NM: String,
    val LI_MN: String, // 리명
    val LTNO_NM: String, //지번명
    val KIND: String, // 국유림/사유림
    val _id : String

)