package com.foxconn.norman.videonews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.foxconn.norman.videoplayer.part.SimpleVideoPlayer;


public class TestActivity extends AppCompatActivity {

    private SimpleVideoPlayer simpleVideoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Log.v("aaa","onCreate执行了");

        simpleVideoPlayer = (SimpleVideoPlayer) findViewById(R.id.test_svp);

        simpleVideoPlayer.setVideoPath(VideoUrlRes.getTestVideo1());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("aaa","onResume执行了");
        simpleVideoPlayer.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        simpleVideoPlayer.onPause();
    }
}
