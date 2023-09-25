package com.example.myapplication1456465;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText textField;
    Button btn;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textField = (EditText)findViewById(R.id.textField);
        btn =(Button)findViewById(R.id.btn);
        text = (TextView)findViewById(R.id.text);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(textField.getText().length()!=0) {
                            double num = Double.parseDouble(String.valueOf(textField.getText()));
                            num = num * 9 / 5 + 32;
                            textField.setText("");
                            text.setText(Double.toString(num)+ "°F");
                        }

                    }
                }
        );

    }
}