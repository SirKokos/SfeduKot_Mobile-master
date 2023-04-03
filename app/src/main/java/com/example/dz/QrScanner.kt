package com.example.dz

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dz.api.sendUser.RestApiServiceSendData
import com.example.dz.api.sendUser.UserInfo
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

class QrScanner : AppCompatActivity(),ZBarScannerView.ResultHandler{
    private lateinit var zbView: ZBarScannerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zbView = ZBarScannerView(this)
        setContentView(zbView)
    }
    override fun onResume() {
        super.onResume()
        zbView.setResultHandler(this)
        zbView.startCamera()
    }
    override fun onPause() {
        super.onPause()
        zbView.stopCamera()
    }
    override fun handleResult(result: Result?) {

        val sPref = getSharedPreferences("userInfo", MODE_PRIVATE)

        val apiService = RestApiServiceSendData()
        apiService.sendToServer(UserInfo(sPref.getLong("userId", 0), sPref.getInt("userHashcode", 0), result!!.contents)){
            Toast.makeText(this, it?.message, Toast.LENGTH_LONG).show()
        }
        
        finish()
    }

}
