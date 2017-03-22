package com.foxconn.norman.videonews.Bobmapi.other;

import com.foxconn.norman.videonews.Bobmapi.entity.UserEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    private Retrofit retrofit_cloud;//用于新接口
    private NewsApi newsApi_cloud;//用于新接口
    private UserApi userApi;
    private NewsApi newsApi;
    private BombClient(){
      HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      okHttpClient=new OkHttpClient.Builder()
              .addInterceptor(new CustomBobmHeaderInterceptor())
              .addInterceptor(interceptor)
              .build();
        //让Gson能够将bomb返回的时间戳自动转换为Date对象
        Gson gson=new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.bmob.cn/")
                .build();
        //构建retrofit_cloud
        retrofit_cloud = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://cloud.bmob.cn/")
                .addConverterFactory(GsonConverterFactory.create(gson))
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
    public NewsApi getNewsApi(){
        if (newsApi==null){
           newsApi=retrofit.create(NewsApi.class);
        }
        return newsApi;
    }
    //拿到newsApi_cloud
    public NewsApi getNewsApi_cloud(){
        if (newsApi_cloud == null){
            newsApi_cloud = retrofit_cloud.create(NewsApi.class);
        }
        return newsApi_cloud;
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
