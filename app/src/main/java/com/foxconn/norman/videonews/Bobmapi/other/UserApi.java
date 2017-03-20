package com.foxconn.norman.videonews.Bobmapi.other;

import com.foxconn.norman.videonews.Bobmapi.entity.UserEntity;
import com.foxconn.norman.videonews.Bobmapi.result.UserResult;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/3/20 0020.
 */

public interface UserApi {
    @GET("1/login")
    Call<UserResult> Login(@Query("username") String username, @Query("password") String password);
    @POST("1/users")
    Call<UserResult> Register(@Body UserEntity requestBody);
}
