package com.foxconn.norman.videoplayer.part;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.foxconn.norman.videoplayer.R;

import io.vov.vitamio.Vitamio;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class SimpleVideoPlayer extends FrameLayout{
    private String videoPath;
    public SimpleVideoPlayer(Context context) {
        super(context,null);
    }

    public SimpleVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public SimpleVideoPlayer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Vitamio.isInitialized(getContext());
        LayoutInflater.from(getContext()).inflate(R.layout.view_simple_video_player,this,true);
        //初始化SurfaceView
        initSurfaceView();
        //初始化视频播放控制视图
        initControllerViews();
    }

    private void initSurfaceView() {}
    private void initControllerViews(){}
    //设置数据源
    public void setVideoPath(String videoPath){
        this.videoPath = videoPath;
    }
    //初始化状态(在Activity的onResume调用)
    public void onResume(){
        //初始化MediaPlayer
//        initMediaPlayer();
        //准备MediaPlayer
//        prepareMediaPlayer();
    }

    //释放状态(在Activity的onPause调用)
    public void onPause(){
        //暂停MediaPlayer
//        pauseMediaPlayer();
        //释放MediaPlayer
//        releaseMediaPlayer();
    }

}
