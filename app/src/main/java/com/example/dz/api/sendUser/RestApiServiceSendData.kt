package com.example.dz.api.sendUser

import com.example.dz.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiServiceSendData{

    fun sendToServer(form: UserInfo, onResult: (BaseResponseBody?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApiSendUser::class.java)

        retrofit.sendToServer(form).enqueue(
            object : Callback<BaseResponseBody> {
                override fun onFailure(call: Call<BaseResponseBody>, t:Throwable){
                    t.printStackTrace()
                    onResult(null)
                }
                override fun onResponse(call: Call<BaseResponseBody>, response: Response<BaseResponseBody>){
                    onResult(response.body())
                }
            }
        )
    }

}