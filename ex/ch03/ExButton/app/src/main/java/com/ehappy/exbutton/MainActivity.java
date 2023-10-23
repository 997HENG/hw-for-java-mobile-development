package com.ehappy.exbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnDo;
    private TextView txtShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //取得介面元件
        btnDo = (Button)findViewById(R.id.button);
        txtShow = (TextView)findViewById(R.id.textView);

        //為Button元件加入Click事件的偵聽，觸發時執行自訂方法 btnDoListener
        btnDo.setOnClickListener(btnDoListener);
    }

    private Button.OnClickListener btnDoListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            txtShow.setText("您按到我了!");
        }
    };
}
