package com.example.dz.api.userInfo

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApiUserInfo {

    @Headers("Content-Type: application/json")
    @POST("data")
    fun sendToServer(@Body form: UserDataForm): Call<UserDataResponseBody>

}