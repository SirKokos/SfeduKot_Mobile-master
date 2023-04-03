package com.example.dz.api.registration

import com.example.dz.api.login.LoginResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApiRegistration {

    @Headers("Content-Type: application/json")
    @POST("newperson")
    fun sendToServer(@Body form: RegistrationForm): Call<LoginResponseBody>

}