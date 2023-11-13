package com.example.listview_with_text_and_image;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lst;
    int [] hotel = new int[]{R.drawable.h1,R.drawable.h2,R.drawable.h3};
    String[] names= new String[]{"金陵飯店","煙波大飯店","圓山大飯店"};
    String[] remains= new String[]{"剩餘:5間","剩餘:4間","剩餘:3間"};
    String[] ratings= new String[]{"評價:1星","評價:2星","評價:3星"};
    String[] prices= new String[]{"$3000","$6000","$9000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setLogo(R.drawable.t1);
        getSupportActionBar().setDisplayUseLogoEnabled(true);



        lst = (ListView)findViewById(R.id.listView);
        final MyAdapter adapter = new MyAdapter(this);
        lst.setAdapter(adapter);
        lst.setOnItemClickListener(new ListView.OnItemClickListener(

        ){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               new AlertDialog.Builder(MainActivity.this).setTitle("trivago").setMessage(names[i]+"\n"+remains[i]+"\n"+ratings[i]+"\n\n"+prices[i]).setPositiveButton(
                       "OK",new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                           }
                       }
               ).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.m1:
                new AlertDialog.Builder(MainActivity.this).setTitle("trivago").setMessage("trivago 飯店搜尋讓用戶輕鬆點選幾下，就可以從數百個訂房網站及超過 190 個國家的 500 多萬家飯店等類型住宿中，找到最優惠的價格。我們每年幫助數百萬名旅客比較飯店等住宿價格。您可以在 trivago 上找到週末短程旅遊的資訊，如東京或大阪，簡單迅速就能找到適合您的飯店。您同樣也可以找適合旅行一週或以上的城市，如紐約及其周邊的飯店。").setPositiveButton(
                        "OK",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }
                ).show();
                break;
            case R.id.m2:
                new AlertDialog.Builder(MainActivity.this).setTitle("how to get there" ).setMessage("坐車").setPositiveButton(
                        "OK",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }
                ).show();
                break;
            case R.id.m3:
                new AlertDialog.Builder(MainActivity.this).setTitle("reservation").setMessage("很貴").setPositiveButton(
                        "OK",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }
                ).show();
                break;
            case R.id.m4:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //////////////////////////
    public class MyAdapter extends BaseAdapter {
        private LayoutInflater myInflater;
        public MyAdapter(Context c){
            myInflater = LayoutInflater.from(c);
        }
        @Override
        public int getCount(){
            return names.length;
        }
        @Override
        public Object getItem(int position){
            return null;
        }
        @Override
        public long getItemId(int position){
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            convertView = myInflater.inflate(R.layout.list, null);

            // 取得 mylayout.xml 中的元件
            ImageView imgLogo = (ImageView) convertView.findViewById(R.id.logo);
            TextView name = ((TextView) convertView.findViewById(R.id.name));
            TextView remain = ((TextView) convertView.findViewById(R.id.remain));
            TextView rating = ((TextView) convertView.findViewById(R.id.rating));
            TextView price = ((TextView) convertView.findViewById(R.id.price));

            // 設定元件內容
            imgLogo.setImageResource(hotel[position]);
            name.setText(names[position]);
            remain.setText(remains[position]);
            rating.setText(ratings[position]);
            price.setText(prices[position]);

            return convertView;
        }
    }
}


