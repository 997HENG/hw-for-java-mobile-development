package com.ehappy.exbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 設定 btnDo 元件 onClick 事件為  btnDoListener
        btnDo.setOnClickListener(btnDoListener)
    }

    // 定義  onClick() 事件的方法
    val btnDoListener = View.OnClickListener { view ->
        txtShow.text="您按到我了"
    }
}