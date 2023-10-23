package com.ehappy.exdebug;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 宣告全域變數
    private EditText edtNum;
    private TextView txtSum;
    private Button btnDo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 取得介面元件
        edtNum=(EditText)findViewById(R.id.edtNum);
        txtSum=(TextView)findViewById(R.id.txtSum);
        btnDo=(Button)findViewById(R.id.btnDo);

        // 設定   Click 事件的 listener
        btnDo.setOnClickListener(btnTranListener);
    }

    private Button.OnClickListener btnTranListener=new Button.OnClickListener(){
        public void onClick(View v){
            int n=Integer.parseInt(edtNum.getText().toString());
            int sum=0;
            for (int i=0;i<=n;i++){
                sum+=i;
            }
            txtSum.setText("總和= " + sum);
        }
    };
}
