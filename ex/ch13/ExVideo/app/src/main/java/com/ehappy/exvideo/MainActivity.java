package com.ehappy.exvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageView imgFront, imgStop, imgPlay, imgPause, imgNext, imgEnd;
    private ListView lstVideo;
    private TextView txtVideo;
    private SurfaceView sufVideo;
    private final String sdPATH= "sdcard/";
    //影片名稱
    String[] videoname=new String[] {"robot", "post", "boat", "coast", "sea"};
    //影片檔案
    String[] videofile=new String[] {"robot.3gp", "post.3gp", "boat.3gp", "coast.3gp", "sea.3gp"};
    private int cListItem=0; //目前播放影片
    private Boolean falgPause=false; //暫停、播放旗標
    private MediaPlayer mediaplayer;
    private SurfaceHolder sufHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgFront=(ImageView)findViewById(R.id.imgFront);
        imgStop=(ImageView)findViewById(R.id.imgStop);
        imgPlay=(ImageView)findViewById(R.id.imgPlay);
        imgPause=(ImageView)findViewById(R.id.imgPause);
        imgNext=(ImageView)findViewById(R.id.imgNext);
        imgEnd=(ImageView)findViewById(R.id.imgEnd);
        lstVideo=(ListView)findViewById(R.id.lstVideo);
        txtVideo=(TextView)findViewById(R.id.txtVideo);
        sufVideo=(SurfaceView)findViewById(R.id.sufVideo);
        imgFront.setOnClickListener(listener);
        imgStop.setOnClickListener(listener);
        imgPlay.setOnClickListener(listener);
        imgPause.setOnClickListener(listener);
        imgNext.setOnClickListener(listener);
        imgEnd.setOnClickListener(listener);
        lstVideo.setOnItemClickListener(lstListener);
        ArrayAdapter<String> adaSong=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, videoname);
        lstVideo.setAdapter(adaSong);
        mediaplayer=new MediaPlayer();
        //建立 Surface 相關物件
        sufHolder=sufVideo.getHolder();
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

    private ImageView.OnClickListener listener=new ImageView.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId())
            {
                case R.id.imgFront:  //上一片
                    frontVideo();
                    break;
                case R.id.imgStop:  //停止
                    if (mediaplayer.isPlaying()) { // 是否正在播放
                        mediaplayer.reset(); //釋放資源
                    }
                    break;
                case R.id.imgPlay:  //播放
                    if(falgPause) {  //如果是暫停狀態就繼續播放
                        mediaplayer.start();
                        falgPause=false;
                    } else {  //非暫停則重新播放
                        playVideo(sdPATH + videofile[cListItem]);
                    }
                    break;
                case R.id.imgPause:  //暫停
                    mediaplayer.pause();
                    falgPause=true;
                    break;
                case R.id.imgNext:  //下一片
                    nextVideo();
                    break;
                case R.id.imgEnd:  //結束
                    mediaplayer.release();
                    finish();
                    break;
            }
        }
    };

    private ListView.OnItemClickListener lstListener=new ListView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            cListItem = position; //取得點選位置
            playVideo(sdPATH + videofile[cListItem]); //播放
        }
    };

    private void playVideo(String path) {
        mediaplayer.reset();
        mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaplayer.setDisplay(sufHolder);
        try
        {
            mediaplayer.setDataSource(path); //播放影片路徑
            mediaplayer.prepare();
            mediaplayer.start(); //開始播放
            txtVideo.setText("影片名稱：" + videoname[cListItem]); //更新名稱
            mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer arg0) {
                    nextVideo(); //播放完後播下一片
                }
            });
        } catch (IOException e) {}
        falgPause=false;
    }

    //下一片
    private void nextVideo() {
        cListItem++;
        if (cListItem >= lstVideo.getCount()) //若到最後就移到第一片
            cListItem = 0;
        playVideo(sdPATH + videofile[cListItem]);
    }

    //上一片/
    private void frontVideo() {
        cListItem--;
        if (cListItem < 0)
            cListItem = lstVideo.getCount()-1; //若到第一片就移到最後
        playVideo(sdPATH + videofile[cListItem]);
    }
}
