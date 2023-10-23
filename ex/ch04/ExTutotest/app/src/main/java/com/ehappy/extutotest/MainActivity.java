package com.ehappy.extutotest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1=(Button)findViewById(R.id.btnButton);
        txt1= (TextView)findViewById(R.id.textView);
        btn1.setOnClickListener(listener);
     }

    private Button.OnClickListener listener=new Button.OnClickListener() {

        @Override
        public void onClick(View view) {
            
        }
    };
}
