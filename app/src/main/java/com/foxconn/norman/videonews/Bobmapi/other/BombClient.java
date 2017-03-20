package com.foxconn.norman.videonews.Bobmapi.other;

import com.foxconn.norman.videonews.Bobmapi.entity.UserEntity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/3/17 0017.
 */

public class BombClient {
    private static BombClient bombClient;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private UserApi userApi;
    private BombClient(){
      HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      okHttpClient=new OkHttpClient.Builder()
              .addInterceptor(new CustomBobmHeaderInterceptor())
              .addInterceptor(interceptor)
              .build();
        retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.bmob.cn/")
                .build();
    }
    public static BombClient getBombClient(){
       if (bombClient==null){
           bombClient=new BombClient();
       }
        return bombClient;
    }
    public UserApi getUserApi(){
        if (userApi==null){
            userApi=retrofit.create(UserApi.class);
        }
        return userApi;
    }
    public Call register(String username, String password){
        //构建一个请求的请求体（根据服务器要求）
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("username",username);
//            jsonObject.put("password",password);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String json = jsonObject.toString();
        String json=new Gson().toJson(new UserEntity(username,password));

        RequestBody requestBody = RequestBody.create(null,json);

        Request request = new Request.Builder()
                .url("https://api.bmob.cn/1/users")
                .post(requestBody)
                .build();

        return okHttpClient.newCall(request);
}
    public Call LogIn(String username, String password){
        Request request=new Request.Builder()
                .get()
                .url("https://api.bmob.cn/1/login" + "?"
                        +"username=" + username + "&"
                        +"password=" + password)

                .build();

        return okHttpClient.newCall(request);
    }
}
