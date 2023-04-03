package com.example.dz.api.userInfo

data class UserDataResponseBody(
    val name: String?,
    val surname: String?,
    val patronymic: String?,
    val role: String?,
    val division: String?,
    val faculty: String?,
    val specialization: String?,
    val course: Short?,
    val groupNum: Short?,
    val info: String?
)