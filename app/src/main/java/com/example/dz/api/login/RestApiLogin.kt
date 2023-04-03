package com.example.dz.api.login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApiLogin {

    @Headers("Content-Type: application/json")
    @POST("login")
    fun sendToServer(@Body form: LoginForm): Call<LoginResponseBody>

}