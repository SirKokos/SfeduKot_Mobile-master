package com.example.dz.api.sendUser

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApiSendUser {

    @Headers("Content-Type: application/json")
    @POST("qr")
    fun sendToServer(@Body form: UserInfo): Call<BaseResponseBody>

}