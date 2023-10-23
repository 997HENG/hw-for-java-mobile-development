package com.ehappy.extrycatch;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtX,edtY;
    private TextView txtResult;
    private Button btnDo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 取得介面元件 id
        edtX=(EditText)findViewById(R.id.edtX);
        edtY=(EditText)findViewById(R.id.edtY);
        txtResult=(TextView)findViewById(R.id.txtResult);
        btnDo=(Button)findViewById(R.id.btnDo);

        // 設定  button 元件 的 listener 為  btnDoListener
        btnDo.setOnClickListener(btnDoListener);
    }

    // 定義  onClick() 方法
    private Button.OnClickListener btnDoListener=new Button.OnClickListener(){
        public void onClick(View v){
            try {
                int x=Integer.parseInt(edtX.getText().toString());
                int y=Integer.parseInt(edtY.getText().toString());
                int r= x % y;
                txtResult.setText(x + " % " + y + " = " + r);
            } catch (NumberFormatException err){
                Toast toast = Toast.makeText(getApplicationContext(), "發生輸入非數值的錯誤!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } catch (Exception err){
                Toast toast = Toast.makeText(getApplicationContext(), "發生其他的錯誤,，包括 分母為  0  的錯誤!\n\r錯誤資訊：" + err.toString(), Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } finally{
                Toast.makeText(getApplicationContext(), "finally 中一律執行!", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
