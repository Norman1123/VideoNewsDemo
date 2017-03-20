package com.foxconn.norman.videonews.Bobmapi.other;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public class CustomBobmHeaderInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        Request.Builder builder=request.newBuilder();
        //用于让bomb服务器，区分是哪一个应用
        builder.addHeader("X-Bmob-Application-Id", BombConst.APPLICATION_ID);
        //用于授权
        builder.addHeader("X-Bmob-REST-API-Key", BombConst.REST_API_KEY);
        //请求和响应统一使用json格式
        builder.addHeader("Content-Type","application/json");
        request=builder.build();
        Response response=chain.proceed(request);
        return response;
    }
}
