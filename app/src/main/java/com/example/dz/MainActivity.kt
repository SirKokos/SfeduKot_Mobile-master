package com.example.dz

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    private var getQrBtn: Button? = null
    private var userDataBtn: Button? = null

    private val MY_PERMISSIONS_REQUEST_CAMERA = 100

    override fun onStart() {
        if(!getSharedPreferences("userInfo", MODE_PRIVATE).contains("userId"))
            startActivity(Intent(this, LoginActivity::class.java))
        super.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getQrBtn = findViewById(R.id.getQrBtn)
        userDataBtn = findViewById(R.id.userDataBtn)

        getQrBtn?.setOnClickListener {
             checkCamPermission()
        }


        userDataBtn?.setOnClickListener {
            startActivity(Intent(this, UpdateActivity::class.java))
        }
    }


    override fun onResume() {
        super.onResume()
        val sPref = getSharedPreferences("userInfo", MODE_PRIVATE)

        findViewById<TextView>(R.id.userNameTextView).text = sPref.getString("userWelcomer", "котик")

    }

    private fun checkCamPermission(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), MY_PERMISSIONS_REQUEST_CAMERA)
        else
            startActivity((Intent(this, QrScanner::class.java)))
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == MY_PERMISSIONS_REQUEST_CAMERA)
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                startActivity((Intent(this, QrScanner::class.java)))

    }

}