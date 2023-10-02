package com.example.toast_test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder dialog ;
    Button btn;
    CheckBox chk,main1,main2,main3,drink1,drink2,drink3;
    int cnt;
    String title="買餐資訊",message="你共訂購\n";
    int [] id = {R.id.main1,R.id.main2,R.id.main3,R.id.drink1,R.id.drink2,R.id.drink3};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FOR BTN INI & SET DIALOG
        btn=(Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i:id){
                    chk = (CheckBox)findViewById(i);

                   if(chk.isChecked()){
                       switch (i){
                           case R.id.main1:
                               message=message+"牛肉飯 ";
                               cnt+=100;
                               break;
                           case R.id.main2:
                               message=message+"豬排飯 ";
                               cnt+=80;
                               break;
                           case R.id.main3:
                               message=message+"蝦仁炒飯 ";
                               cnt+=60;
                               break;
                           case R.id.drink1:
                               message=message+"紅茶 ";
                               cnt+=20;
                               break;
                           case R.id.drink2:
                               message=message+"綠茶 ";
                               cnt+=25;
                               break;
                           case R.id.drink3:
                               message=message+"咖啡 ";
                               cnt+=50;
                               break;
                       }
                   }
                }



                //triggered after took order
               if(cnt>0){
                   message =message+ "\n"+"共"+cnt+"元"+"\n";
                   dialog = new AlertDialog.Builder(MainActivity.this);
                   dialog.setTitle(title).setMessage(message).setPositiveButton("確認", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                            cnt=0;
                           message="你共訂購\n";
                           for(int it:id){
                               chk = (CheckBox)findViewById(it);
                               chk.setChecked(false);
                               }

                       }
                   }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           for(int it:id){
                               chk = (CheckBox)findViewById(it);
                               chk.setChecked(false);
                           }
                       }
                   }).show();
               }

            }
        });



    }




}