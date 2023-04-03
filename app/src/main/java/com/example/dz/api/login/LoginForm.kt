package com.example.dz.api.login

import com.google.gson.annotations.SerializedName


data class LoginForm(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
)