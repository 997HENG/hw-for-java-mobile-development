package com.ehappy.exvideoview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private TextView txtVideo;
    private VideoView vidVideo;
    private Button btnVideo1, btnVideo2, btnEnd;
    private final String sdPath= "sdcard/"; //SD卡路徑
    private String fname = ""; //影片檔名稱

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVideo = (TextView)findViewById(R.id.txtVideo);
        vidVideo = (VideoView)findViewById(R.id.vidVideo);
        btnVideo1 = (Button)findViewById(R.id.btnVideo1);
        btnVideo2 = (Button)findViewById(R.id.btnVideo2);
        btnEnd = (Button)findViewById(R.id.btnEnd);
        btnVideo1.setOnClickListener(listener);
        btnVideo2.setOnClickListener(listener);
        btnEnd.setOnClickListener(listener);
        vidVideo.setOnPreparedListener(listenprepare); //監聽影片播放中
        vidVideo.setOnCompletionListener(listencomplete);  //監聽影片結束
        requestStoragePermission();
    }

    private void requestStoragePermission() {
        if(Build.VERSION.SDK_INT >= 23) {  //Androis 6.0 以上
            //判斷是否已取得驗證
            int hasPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if(hasPermission != PackageManager.PERMISSION_GRANTED) {  //未取得驗證
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (!(grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(this, "未取得權限！", Toast.LENGTH_SHORT).show();
                finish();  //結束應用程式
            }
        } else  {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private Button.OnClickListener listener=new Button.OnClickListener(){
        @Override
        public void onClick(View v)
        {
            switch(v.getId())
            {
                case R.id.btnVideo1:  //影片一
                    fname = "robot.3gp";
                    playVideo(sdPath+fname);
                    break;
                case R.id.btnVideo2:  //影片二
                    fname = "post.3gp";
                    playVideo(sdPath+fname);
                    break;
                case R.id.btnEnd:  //結束
                    finish();
            }
        }
    };

    //播放影片
    private void playVideo(String filePath) { //filePath是影片路徑
        if(filePath!="")
        {
            vidVideo.setVideoPath(filePath); //設定影片路徑
            //加入播放控制軸
            vidVideo.setMediaController(new MediaController(MainActivity.this));
            vidVideo.start(); //開始播放
        }
    }

    private MediaPlayer.OnPreparedListener listenprepare=new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            txtVideo.setText("影片：" + fname);
        }
    };

    private MediaPlayer.OnCompletionListener listencomplete=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            txtVideo.setText(fname + " 播放完畢！");
        }
    };
}
