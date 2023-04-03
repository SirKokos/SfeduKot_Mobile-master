package com.example.dz.api.userInfo

import com.google.gson.annotations.SerializedName

data class UserDataForm(
    @SerializedName("id") val id: Long,
    @SerializedName("hashCode") val hashCode: Int
)