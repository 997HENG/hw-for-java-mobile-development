package com.ehappy.exphotoplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    // 將所有的圖片儲存至陣列中
    int[] imgId={R.drawable.img01,R.drawable.img02,R.drawable.img03,
            R.drawable.img04,R.drawable.img05,R.drawable.img06};
    private Button btnPrev,btnNext;
    private ImageView imgPhoto;
    int p=0; // 圖片的索引(第幾張圖片)
    int count=imgId.length; // 共有多少張圖片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 取得介面元件
        btnPrev=(Button)findViewById(R.id.btnPrev);
        btnNext=(Button)findViewById(R.id.btnNext);
        imgPhoto=(ImageView)findViewById(R.id.imgPhoto);

        // 設定 button 元件 Click 事件的 listener
        btnPrev.setOnClickListener(btnPrevListener);
        btnNext.setOnClickListener(btnNextListener);
    }

    // 定義 btnPrev 按鈕的onClick()方法
    private Button.OnClickListener btnPrevListener=new Button.OnClickListener(){
        public void onClick(View v){
            p--;
            if (p<0)
                p=count-1;
            imgPhoto.setImageResource(imgId[p]);
            setTitle("第" + (p+1) + "/" + count);
        }
    };

    // 定義 btnNext 按鈕的onClick() 方法
    private Button.OnClickListener btnNextListener=new Button.OnClickListener(){
        public void onClick(View v){
            p++;
            if (p==count)
                p=0;
            imgPhoto.setImageResource(imgId[p]);
            setTitle("第" + (p+1) + "/" + count);
        }
    };
}