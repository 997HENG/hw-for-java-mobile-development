package com.example.image_gridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Spinner spn;
    int [] images ={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6};
    int [] costGrid ={100,170,110,150,130,160};
    String strGrid;
    int indexGrid;
    boolean flag=false;




    String [] strSpn={"加購薯條$30","加購咖啡$40","加購可樂$50","加購冰紅茶$20"};
    String [] strSpnItem={"薯條","咖啡","可樂","冰紅茶"};
    int [] costSpn = {30,40,50,20};


   private TextView order,count;
   private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //!for textview
         order = (TextView)findViewById(R.id.order);
         count = (TextView)findViewById(R.id.count);

        //!for gridview
        MyAdapter myAdapter = new MyAdapter(this);
        GridView gridView = (GridView) findViewById(R.id.grid);
        gridView.setAdapter(myAdapter);
        imageView = (ImageView) findViewById(R.id.img);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                imageView.setImageResource(images[i]);
                order.setText("你選擇: "+(i+1)+" 號餐 $"+costGrid[i]+" 元");
                strGrid=(i+1)+"號餐加";
                indexGrid=i;
                flag = true;
                count.setText("你共購買" + strGrid + strSpnItem[spn.getSelectedItemPosition()] + "共" + (costGrid[indexGrid] + costSpn[spn.getSelectedItemPosition()]) + " 元");
            }
        });


        //!for spinner
        spn = (Spinner) findViewById(R.id.spn);
        ArrayAdapter<String > adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,strSpn);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(
                new Spinner.OnItemSelectedListener(){
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        count.setText("你共購買" +strSpnItem[i] + "共" + (costSpn[i]) + " 元");
                        if(flag) {
                            count.setText("你共購買" + strGrid + strSpnItem[i] + "共" + (costGrid[indexGrid] + costSpn[i]) + " 元");
                        }

                    }
                }
        );

    }

//    private Spinner.OnItemSelectedListener spnListener =
//            new Spinner.OnItemSelectedListener(){
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
//
//                }
//            }
// 自訂的 MyAdapter 類別，繼承 BaseAdapter 類別
class MyAdapter extends BaseAdapter {
    private Context mContext;
    public MyAdapter(Context c){
        mContext=c;
    }
    @Override
    public int getCount(){
        return images.length; // 圖片共有多少張
    }
    @Override
    public Object getItem(int position){
        return position;
    }
    @Override
    public long getItemId(int position){
        return position; // 目前圖片索引
    }

    // 定義 GridView 顯示的圖片
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ImageView iv = new ImageView(mContext);
        iv.setImageResource(images[position]);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iv.setLayoutParams(new GridView.LayoutParams(320,240));
        return iv;
    }
}

}