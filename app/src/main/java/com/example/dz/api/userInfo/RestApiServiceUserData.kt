package com.example.dz.api.userInfo

import android.util.Log
import com.example.dz.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestApiServiceUserData{

    fun sendToServer(form: UserDataForm, onResult: (UserDataResponseBody?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApiUserInfo::class.java)
        retrofit.sendToServer(form).enqueue(
            object : Callback<UserDataResponseBody> {
                override fun onFailure(call: Call<UserDataResponseBody>, t:Throwable){
                    t.printStackTrace()
                    onResult(null)
                }
                override fun onResponse(call: Call<UserDataResponseBody>, response: Response<UserDataResponseBody>){
                    onResult(response.body())
                }
            }
        )
    }

}