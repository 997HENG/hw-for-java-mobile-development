package com.ehappy.exmiletokm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 設定 btnTran 元件 onClick 事件為  btnTranListener
        btnTran.setOnClickListener(btnTranListener)
    }

    // 定義  onClick() 事件的方法
    val btnTranListener = View.OnClickListener { view ->
        val miles=edtMile.text.toString().toDouble()
        var km:Double = 1.61*miles
        txtKm.text="時速=  $km 公里"
    }
}