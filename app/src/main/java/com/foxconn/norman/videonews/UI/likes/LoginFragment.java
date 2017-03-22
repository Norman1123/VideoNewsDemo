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
import com.foxconn.norman.videonews.Bobmapi.result.ErrorResult;
import com.foxconn.norman.videonews.Bobmapi.result.UserResult;
import com.foxconn.norman.videonews.Commons.ToastUtils;
import com.foxconn.norman.videonews.R;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Administrator on 2017/3/15 0015.
 */

public class LoginFragment extends DialogFragment{

    private Unbinder mUnbinder;

    @BindView(R.id.etUsername)
    EditText mEtUsername;
    @BindView(R.id.etPassword)
    EditText mEtPassword;
    @BindView(R.id.btnLogin)
    Button mBtnLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //无标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_login, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btnLogin)
    public void onClick(){
        final String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();
        //用户名和密码不能为空
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            ToastUtils.showShort(R.string.username_or_password_can_not_be_null);
            return;
        }
        //显示进度条
        mBtnLogin.setVisibility(View.GONE);

        // TODO: 2017/3/15 0015 登录的网络请求
       Call<UserResult> call=BombClient.getBombClient().getUserApi().Login(username,password);
        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, retrofit2.Response<UserResult> response) {
                //隐藏进度条
                mBtnLogin.setVisibility(View.VISIBLE);
                //登录未成功
                if (!response.isSuccessful()){
                    try {
                        String error = response.errorBody().string();
                        ErrorResult errorResult = new Gson().fromJson(error,ErrorResult.class);
                        ToastUtils.showShort(errorResult.getError());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                //登录成功
                UserResult result = response.body();
                if (listener!=null){
                listener.loginSuccess(username,result.getObjectId());}
                ToastUtils.showShort(R.string.login_success);
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                //隐藏进度条
                mBtnLogin.setVisibility(View.VISIBLE);
                ToastUtils.showShort(t.getMessage());
            }
        });


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
    public interface OnLoginSuccessListener{
        void loginSuccess(String username,String objectId);
    }
    private OnLoginSuccessListener listener;
    public void setLoginListener(OnLoginSuccessListener listener){
        this.listener=listener;
    }
}
