package com.ehappy.exactivity01;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFinish=(Button)findViewById(R.id.btnFinish);
        Button btnDial=(Button)findViewById(R.id.btnDial);
        Button btnPage2=(Button)findViewById(R.id.btnPage2);

        // 設定 button 元件 Click 事件共用 myListener
        btnFinish.setOnClickListener(myListner);
        btnDial.setOnClickListener(myListner);
        btnPage2.setOnClickListener(myListner);
        Toast.makeText(getApplicationContext(),"onCreate(1)",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),"onStart(1)",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(),"onResume(1)",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(),"onRestart(1)",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause(1)", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop(1)", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy(1)", Toast.LENGTH_SHORT).show();
    }

    private Button.OnClickListener myListner=new Button.OnClickListener(){
        public void onClick(View v){
            if (v.getId()==R.id.btnDial) { // 呼叫內建的 Activity
                Uri uri = Uri.parse("tel:0999123456");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }else if (v.getId()==R.id.btnPage2){	// 呼叫另一個自建的 Activity
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,Second.class);
                startActivity(intent);
            }
            else if (v.getId()==R.id.btnFinish) // 結束程式
                finish();
        }
    };
}
