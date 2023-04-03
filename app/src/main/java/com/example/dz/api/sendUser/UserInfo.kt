package com.example.dz.api.sendUser

import com.google.gson.annotations.SerializedName

data class UserInfo (

    @SerializedName("userId") val userId: Long,
    @SerializedName("dataHashCode") val dataHashCode: Int,
    @SerializedName("randomKey") val randomKey: String

)