package com.ehappy.exlistview02;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtResult;
    private ListView lstPrefer;
    String[] Balls= new String[] {"basketball","soccer","baseball","籃球","足球","棒球"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 取得資源類別檔中的介面元件
        txtResult=(TextView) findViewById(R.id.txtResult);
        lstPrefer=(ListView) findViewById(R.id.lstPrefer);

        // 建立 ArrayAdapter
        ArrayAdapter<String> adapterBalls=new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,Balls);

        // 設定 ListView 的資料來源
        lstPrefer.setAdapter(adapterBalls);

        // 設定 lstPrefer 元件 ItemClick 事件的 listener為 lstPreferListener
        lstPrefer.setOnItemClickListener(lstPreferListener);

        // 改變 ListView 的屬性
        lstPrefer.setTextFilterEnabled(true); //啟用按鍵過濾功能
        lstPrefer.setSelector(R.drawable.green); //改變選取項目的背景色為圖片檔
    }

    // 定義 onItemClick 方法
    private ListView.OnItemClickListener lstPreferListener=
      new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // 顯示 ListView 的選項內容
            String sel=parent.getItemAtPosition(position).toString();
            txtResult.setText("我最喜歡的球類運動是 : " + sel);
        }
    };
}