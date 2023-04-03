package com.example.dz.api.login

import com.example.dz.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestApiServiceLogin{

    fun sendToServer(form: LoginForm, onResult: (LoginResponseBody?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApiLogin::class.java)
        retrofit.sendToServer(form).enqueue(
            object : Callback<LoginResponseBody> {
                override fun onFailure(call: Call<LoginResponseBody>, t:Throwable){
                    t.printStackTrace()
                    onResult(null)
                }
                override fun onResponse(call: Call<LoginResponseBody>, response: Response<LoginResponseBody>){
                    onResult(response.body())
                }
            }
        )
    }

}