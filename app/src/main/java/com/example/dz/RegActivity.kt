package com.example.dz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.dz.api.registration.RegistrationForm
import com.example.dz.api.registration.RestApiServiceRegistration
import com.example.dz.api.userInfo.RestApiServiceUserData
import com.example.dz.api.userInfo.UserDataForm

class RegActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        val regBtn: Button = findViewById(R.id.confirmRegBtn)
        val email = findViewById<EditText?>(R.id.email).text
        val password = findViewById<EditText?>(R.id.password).text.toString()
        val passwordConfirm = findViewById<EditText?>(R.id.passwordConfirm).text.toString()
        val error: TextView = findViewById(R.id.regError)


        regBtn.setOnClickListener {

            val sPref = getSharedPreferences("userInfo", MODE_PRIVATE)
            var welcomer: String

            if(password != passwordConfirm){
                error.text = "Пароли не совпадают!"
                println(password)
                println(passwordConfirm)
            }
            else{

                val apiService = RestApiServiceRegistration()
                apiService.sendToServer(RegistrationForm(email.toString(), password)){
                    val id = it?.id
                    val hashCode = it?.hashCode

                    Log.wtf("AAAAA", id.toString())
                    Log.wtf("AAAAA", hashCode.toString())

                    if(!it!!.access){
                        error.text = it.message
                    }else{

                        if(id == null || hashCode == null)
                            Toast.makeText(this, "Ошибка! Проверьте подключение!", Toast.LENGTH_LONG).show()
                        else {

                            sPref.edit().putLong("userId", id).apply()
                            sPref.edit().putInt("userHashcode", hashCode).apply()

                            welcomer = "котик"

                            val apiServiceGetData = RestApiServiceUserData()
                            apiServiceGetData.sendToServer(UserDataForm(id, hashCode)) { body ->

                                if (!body?.name.isNullOrEmpty()) {
                                    welcomer =
                                        if (!body?.patronymic.isNullOrEmpty()) {
                                            body?.name.plus(" ").plus(body?.patronymic)
                                        } else {
                                            body?.name.toString()
                                        }
                                }
                                sPref.edit().putString("userWelcomer", welcomer).apply()
                            }
                        }
                        startActivity((Intent(this, MainActivity::class.java)))
                    }
                }

            }

        }


    }
}