package com.example.dz.api.registration

import com.google.gson.annotations.SerializedName


data class RegistrationForm(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
    )