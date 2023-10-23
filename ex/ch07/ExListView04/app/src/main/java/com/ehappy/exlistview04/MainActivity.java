package com.ehappy.exlistview04;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView lstPrefer;
    int[] resIds = new int[]{ R.drawable.basketball, R.drawable.football,R.drawable.baseball, R.drawable.other};
    String[] Balls= new String[] {"籃球","足球","棒球","其他"};
    String[] engNames = { "Basket Ball", "Foot Ball","Base Ball","Other"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 取得介面元件
        lstPrefer=(ListView)findViewById(R.id.lstPrefer);

        // 建立自訂的 Adapter
        MyAdapter adapter=new MyAdapter(this);

        // 設定 ListView 的資料來源
        lstPrefer.setAdapter(adapter);
    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater myInflater;
        public MyAdapter(Context c){
            myInflater = LayoutInflater.from(c);
        }
        @Override
        public int getCount(){
            return Balls.length;
        }
        @Override
        public Object getItem(int position){
            return Balls[position];
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            convertView = myInflater.inflate(R.layout.mylayout, null);

            // 取得 mylayout.xml 中的元件
            ImageView imgLogo = (ImageView) convertView.findViewById(R.id.imgLogo);
            TextView txtName = ((TextView) convertView.findViewById(R.id.txtName));
            TextView txtengName = ((TextView) convertView.findViewById(R.id.txtengName));

            // 設定元件內容
            imgLogo.setImageResource(resIds[position]);
            txtName.setText(Balls[position]);
            //或 txtName.setText(""+getItem(position));
            txtengName.setText(engNames[position]);

            return convertView;
        }
    }
}