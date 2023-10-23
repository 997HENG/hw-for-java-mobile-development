package com.ehappy.exwelcome;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView txtName;
    private EditText edtName;
    private Button btnEnd, btnClear;
    private String sname, msg;
    private SharedPreferences preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = (TextView)findViewById(R.id.txtName);
        edtName = (EditText)findViewById(R.id.edtName);
        btnEnd = (Button)findViewById(R.id.btnEnd);
        btnClear = (Button)findViewById(R.id.btnClear);
        btnEnd.setOnClickListener(listener);
        btnClear.setOnClickListener(listener);

        preference=getSharedPreferences("preFile", MODE_PRIVATE); //建立儲存檔
        sname=preference.getString("name", ""); //讀取資料
        //如果未建立資料就顯示輸入欄位
        if(sname.equals("")) {
            txtName.setVisibility(TextView.VISIBLE);
            edtName.setVisibility(TextView.VISIBLE);
            btnClear.setVisibility(Button.INVISIBLE);
            msg="歡迎使用本應用程式！\n你尚未建立基本資料，請輸入姓名！";
        } else {   //已建立資料就顯示歡迎訊息
            msg="親愛的 " + sname + "，你好！\n歡迎再次使用本應用程式！";
        }
        //彈出式歡迎視窗
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("歡迎使用本軟體！")
                .setMessage(msg)
                .setPositiveButton("確定", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialoginterface, int i)
                    {	}
                })
                .show();
    }

    private Button.OnClickListener listener=new Button.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btnEnd: //按結束鈕
                    finish();
                    break;
                case R.id.btnClear: //按清除資料鈕
                    if(!sname.equals("")) {
                        preference.edit()
                                .clear()
                                .commit();
                        Toast.makeText(getApplicationContext(), "所有資料都已清除！", Toast.LENGTH_LONG) .show();
                    }
                    btnClear.setVisibility(Button.INVISIBLE);
                    break;
            }
        }
    };

    //結束應用程式前會觸發的事件
    protected void onStop(){
        super.onStop();
        if(sname.equals("")) { //如果未建立資料就將輸入值存檔
            preference.edit()
                    .putString("name", edtName.getText().toString())
                    .commit();
        }
    }
}
