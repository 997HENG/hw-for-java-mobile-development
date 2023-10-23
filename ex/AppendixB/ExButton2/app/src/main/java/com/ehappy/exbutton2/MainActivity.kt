package com.ehappy.exbutton2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 以匿名物件方式設定 btnDo 元件 onClick 事件
        btnDo.setOnClickListener(){
            txtShow.text="您按到我了"
        }
    }
}
