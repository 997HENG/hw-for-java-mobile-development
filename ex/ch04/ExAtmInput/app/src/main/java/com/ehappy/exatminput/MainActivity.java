package com.ehappy.exatminput;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //建立全域變數
    private EditText edtATM;
    private Button btnN1, btnN2, btnN3;
    private Button btnN4, btnN5, btnN6;
    private Button btnN7, btnN8, btnN9, btnN0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //取得介面元件
        edtATM=(EditText)findViewById(R.id.edtATM);
        btnN1=(Button)findViewById(R.id.btnN1);
        btnN2=(Button)findViewById(R.id.btnN2);
        btnN3=(Button)findViewById(R.id.btnN3);
        btnN4=(Button)findViewById(R.id.btnN4);
        btnN5=(Button)findViewById(R.id.btnN5);
        btnN6=(Button)findViewById(R.id.btnN6);
        btnN7=(Button)findViewById(R.id.btnN7);
        btnN8=(Button)findViewById(R.id.btnN8);
        btnN9=(Button)findViewById(R.id.btnN9);
        btnN0=(Button)findViewById(R.id.btnN0);
        //設定共用事件
        btnN1.setOnClickListener(listener);
        btnN2.setOnClickListener(listener);
        btnN3.setOnClickListener(listener);
        btnN4.setOnClickListener(listener);
        btnN5.setOnClickListener(listener);
        btnN6.setOnClickListener(listener);
        btnN7.setOnClickListener(listener);
        btnN8.setOnClickListener(listener);
        btnN9.setOnClickListener(listener);
        btnN0.setOnClickListener(listener);
    }

    private Button.OnClickListener listener=new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btnN1:  //按 1 鈕
                    displayATM("1");
                    break;
                case R.id.btnN2:  //按 2 鈕
                    displayATM("2");
                    break;
                case R.id.btnN3:  //按 3 鈕
                    displayATM("3");
                    break;
                case R.id.btnN4:  //按 4 鈕
                    displayATM("4");
                    break;
                case R.id.btnN5:  //按 5 鈕
                    displayATM("5");
                    break;
                case R.id.btnN6:  //按 6 鈕
                    displayATM("6");
                    break;
                case R.id.btnN7:  //按 7 鈕
                    displayATM("7");
                    break;
                case R.id.btnN8:  //按 8 鈕
                    displayATM("8");
                    break;
                case R.id.btnN9:  //按 9 鈕
                    displayATM("9");
                    break;
                case R.id.btnN0:  //按 0 鈕
                    displayATM("0");
                    break;
            }
        }
    };

    private void displayATM(String s) {
        String str=edtATM.getText().toString();
        edtATM.setText(str + s);
    }
}
