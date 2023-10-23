package com.ehappy.exactivity02;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public MediaPlayer player=new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = MediaPlayer.create(getApplicationContext(), R.raw.music);
        player.setLooping(true); // 循環播放
        player.start();          // 音樂播放
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.pause();  // 音樂播放暫停
    }

    @Override
    protected void onResume() {
        super.onResume();
        player.start();  // 音樂繼續播放
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();   // 釋放
    }
}
