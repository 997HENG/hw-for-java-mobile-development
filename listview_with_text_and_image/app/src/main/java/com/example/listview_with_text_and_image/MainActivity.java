package com.example.listview_with_text_and_image;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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


