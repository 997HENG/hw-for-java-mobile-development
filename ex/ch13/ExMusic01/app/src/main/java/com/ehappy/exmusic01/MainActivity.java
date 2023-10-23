package com.ehappy.exmusic01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
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
    private ListView lstMusic;
    private TextView txtMusic;
    private MediaPlayer mediaplayer;
    private final String SONGPATH= "sdcard/";
    //歌曲名稱
    String[] songname=new String[] {"greensleeves", "mario", "songbird", "summersong", "tradewinds"};
    //歌曲檔案
    String[] songfile=new String[] {"greensleeves.mp3", "mario.mp3", "songbird.mp3", "summersong.mp3", "tradewinds.mp3"};
    private int cListItem=0; //目前播放歌曲
    private Boolean falgPause=false; //暫停旗標
    private ArrayAdapter<String> adaSong;
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
        lstMusic=(ListView)findViewById(R.id.lstMusic);
        txtMusic=(TextView)findViewById(R.id.txtMusic);
        imgFront.setOnClickListener(listener);
        imgStop.setOnClickListener(listener);
        imgPlay.setOnClickListener(listener);
        imgPause.setOnClickListener(listener);
        imgNext.setOnClickListener(listener);
        imgEnd.setOnClickListener(listener);
        lstMusic.setOnItemClickListener(lstListener);
        mediaplayer=new MediaPlayer();
        adaSong=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, songname);
        requestStoragePermission();
    }

    //檢查驗證
    private void requestStoragePermission() {
        if(Build.VERSION.SDK_INT >= 23) {  //Androis 6.0 以上
            //判斷是否已取得驗證
            int hasPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if(hasPermission != PackageManager.PERMISSION_GRANTED) {  //未取得驗證
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                return;
            }
        }
        lstMusic.setAdapter(adaSong);  //已取得驗證
    }

    //requestPermissions 觸發的事件
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {  //按 允許 鈕
                lstMusic.setAdapter(adaSong);
            } else {
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
                case R.id.imgFront:  //上一首
                    frontSong();
                    break;
                case R.id.imgStop:  //停止
                    if (mediaplayer.isPlaying()) { // 是否正在播放
                        mediaplayer.reset(); //重置MediaPlayer
                    }
                    break;
                case R.id.imgPlay:  //播放
                    if(falgPause) { //如果是暫停狀態就繼續播放
                        mediaplayer.start();
                        falgPause=false;
                    } else {  //非暫停則重新播放
                        playSong(SONGPATH + songfile[cListItem]);
                    }
                    break;
                case R.id.imgPause:  //暫停
                    mediaplayer.pause();
                    falgPause=true;
                    break;
                case R.id.imgNext:  //下一首
                    nextSong();
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
            playSong(SONGPATH + songfile[cListItem]); //播放
        }
    };

    private void playSong(String path) {
        try
        {
            mediaplayer.reset();
            mediaplayer.setDataSource(path); //播放歌曲路徑
            mediaplayer.prepare();
            mediaplayer.start(); //開始播放
            txtMusic.setText("歌名：" + songname[cListItem]); //更新歌名
            mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    nextSong(); //播放完後播下一首
                }
            });
        } catch (IOException e) {}
    }

    //下一首歌
    private void nextSong() {
        cListItem++;
        if (cListItem >= lstMusic.getCount()) //若到最後就移到第一首
            cListItem = 0;
        playSong(SONGPATH + songfile[cListItem]);
    }

    //上一首歌/
    private void frontSong() {
        cListItem--;
        if (cListItem < 0)
            cListItem = lstMusic.getCount()-1; //若到第一首就移到最後
        playSong(SONGPATH + songfile[cListItem]);
    }
}
