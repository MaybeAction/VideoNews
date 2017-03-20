package com.example.maybe.videonews.ui.likes;

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

import com.example.maybe.videonews.R;
import com.example.maybe.videonews.bombapi.BombClient;
import com.example.maybe.videonews.bombapi.result.UserResult;
import com.example.maybe.videonews.commons.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
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
        Call call = BombClient.getInstance().register(username,password);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG","没有网络，或者网络连接超时");
                //这是后台线程！！！
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if(response.isSuccessful()){
                        Log.e("okhttp","请求成功，响应吗200-299");
                        //拿到响应体的json格式的字符串
                        String json=response.body().string();
                        //Gson 是一个用来生成，解析json数据的第三方库
                        //生成，可以将一个类（实体类），生成为一串json格式的数据
                        //解析，将一串json格式的数据，生成为一个类（结果类）
                        UserResult userResult = new Gson().fromJson(json,UserResult.class);
                        Log.e("okhttp","objectId = " + userResult.getObjectId());
                    }else{
                        Log.e("okhttp","请求失败，响应码= "+response.code());
                    }
            }
        });

    }

}
