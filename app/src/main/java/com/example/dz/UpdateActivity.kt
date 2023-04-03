package com.example.dz

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dz.api.userInfo.RestApiServiceUserData
import com.example.dz.api.userInfo.UserDataForm

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val name: EditText = findViewById(R.id.name)
        val surname: EditText = findViewById(R.id.surname)
        val patronymic: EditText = findViewById(R.id.patronymic)
        val role: EditText = findViewById(R.id.role)
        val faculty: EditText = findViewById(R.id.faculty)
        val specialization: EditText = findViewById(R.id.specialization)
        val course: EditText = findViewById(R.id.course)
        val groupNum: EditText = findViewById(R.id.groupNum)

        val updBtn: Button = findViewById(R.id.updBtn)


        val sPref = getSharedPreferences("userInfo", MODE_PRIVATE)
        RestApiServiceUserData().sendToServer(
            UserDataForm(
                sPref.getLong("userId", 0),
                sPref.getInt("userHashcode", 0))){

            print(it)

            if(!it?.name.isNullOrEmpty()) name.hint = it?.name
            if(!it?.surname.isNullOrEmpty()) surname.hint = it?.surname
            if(!it?.patronymic.isNullOrEmpty()) patronymic.hint = it?.patronymic
            if(!it?.role.isNullOrEmpty()) role.hint = it?.role
            if(!it?.faculty.isNullOrEmpty()) faculty.hint = it?.faculty
            if(!it?.specialization.isNullOrEmpty()) specialization.hint = it?.specialization
            if(it?.course.toString() != "0") course.hint = it?.course.toString()
            if(it?.groupNum.toString() != "0") groupNum.hint = it?.groupNum.toString()

        }


        updBtn.setOnClickListener {

            Toast.makeText(this, "Не работает", Toast.LENGTH_LONG)

        }

    }
}