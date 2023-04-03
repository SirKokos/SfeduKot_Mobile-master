package com.example.dz.api.registration

import com.example.dz.api.ServiceBuilder
import com.example.dz.api.login.LoginResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestApiServiceRegistration{

    fun sendToServer(form: RegistrationForm, onResult: (LoginResponseBody?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApiRegistration::class.java)
        retrofit.sendToServer(form).enqueue(
            object : Callback<LoginResponseBody> {
                override fun onFailure(call: Call<LoginResponseBody>, t:Throwable){
                    onResult(null)
                }
                override fun onResponse(call: Call<LoginResponseBody>, response: Response<LoginResponseBody>){
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }

}