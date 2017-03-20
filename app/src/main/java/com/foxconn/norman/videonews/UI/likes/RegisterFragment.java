package com.foxconn.norman.videonews.UI.likes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.foxconn.norman.videonews.Bobmapi.other.BombClient;
import com.foxconn.norman.videonews.Bobmapi.result.UserResult;
import com.foxconn.norman.videonews.Commons.ToastUtils;
import com.foxconn.norman.videonews.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class RegisterFragment extends DialogFragment {

    @BindView(R.id.etUsername)
    EditText mEtUsername;
    @BindView(R.id.etPassword)
    EditText mEtPassword;
    @BindView(R.id.btnRegister)
    Button mBtnRegister;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //取消标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_register,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btnRegister)
    public void onClick(){
        final String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();

        //用户名和密码不能为空
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            ToastUtils.showShort(R.string.username_or_password_can_not_be_null);
            return;
        }
        // TODO: 2017/3/15 0015 注册的网络请求
        Call call= BombClient.getBombClient().register(username,password);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //超时或没有网络连接
                Log.e("okhttp","超时或没有网络连接");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               // 判断是否请求成功（响应码=200 -- 299）
                if (response.isSuccessful()){
              //拿到响应体（解析，并展示）
                    String json=response.body().string();
                    UserResult userResult=new Gson().fromJson(json,UserResult.class);
                    Log.e("okhttp","请求成功"+userResult.getCreatedAt());
                }else{
                    Log.e("okhttp","请求失败,响应码 = " +response.code());
                    Log.e("okhttp","请求失败,响应体 = " +response.body().string());
                }
            }
        });

    }

}
