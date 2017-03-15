package com.foxconn.norman.videonews.Commons;

import android.app.Application;



/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class VideoNewsApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化吐丝工具类
        ToastUtils.init(this);
    }
}
