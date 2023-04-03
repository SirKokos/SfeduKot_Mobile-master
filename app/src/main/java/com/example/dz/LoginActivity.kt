package com.example.dz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dz.api.login.LoginForm
import com.example.dz.api.login.RestApiServiceLogin
import com.example.dz.api.userInfo.RestApiServiceUserData
import com.example.dz.api.userInfo.UserDataForm

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginBtn: Button = findViewById(R.id.loginBtn)
        val error: TextView = findViewById(R.id.loginError)
        val regBtn: Button = findViewById(R.id.regBtn)

        loginBtn.setOnClickListener {

            val sPref = getSharedPreferences("userInfo", MODE_PRIVATE)

            val email = findViewById<TextView?>(R.id.email).text.toString()
            val password = findViewById<TextView?>(R.id.password).text.toString()
            var welcomer: String

            val apiService = RestApiServiceLogin()
            apiService.sendToServer(LoginForm(email, password)){
                val id = it?.id
                val hashCode = it?.hashCode

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

        regBtn.setOnClickListener {
            startActivity((Intent(this, RegActivity::class.java)))
        }

    }
}