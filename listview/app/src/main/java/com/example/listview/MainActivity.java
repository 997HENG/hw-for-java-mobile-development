package com.example.listview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    AlertDialog.Builder dialog ;
    Button btn;
    boolean flag=false;
    int cnt;
    String strDialog;


    ListView list;
    String [] strList={"豬排飯$100","雞腿飯$120","蝦仁炒飯$80"};
    int [] costList = {100,120,80};
    int listIndex;



    Spinner spn;
    String [] strSpn={"加購紅茶$20","加購綠茶$15","加購咖啡$40"};
    String [] strSpnItem={"紅茶","綠茶","咖啡"};
    int [] costSpn = {20,15,40};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //!for spinner
        spn = (Spinner) findViewById(R.id.spn);
        ArrayAdapter<String > adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,strSpn);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spn.setAdapter(adapter);

        //!for listView
        list = (ListView)findViewById(R.id.list);
        ArrayAdapter<String> listAd=new ArrayAdapter<String >(this,android.R.layout.simple_list_item_1,strList);
        list.setAdapter(listAd);
        list.setOnItemClickListener(
                new ListView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        flag=true;
                        list.setSelector(R.color.colorPrimary);
                        listIndex=i;
                    }
                }
        );

        //! for btn init and dialog
        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        int position = spn.getSelectedItemPosition();
                        cnt = costSpn[position] + costList[listIndex];
                        strDialog = strList[listIndex] + ", " + strSpn[position];
                        dialog = new AlertDialog.Builder(MainActivity.this);


                        if(flag==false){
                            dialog.setTitle("佳佳速食").setMessage("請選擇餐點").setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }).show();
                        }else{
                            dialog.setTitle("你選擇:").setMessage(strDialog+"\n\n"+"共$"+cnt).setPositiveButton("確認", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }).show();
                    }


                    }
                }
        );

    }
}