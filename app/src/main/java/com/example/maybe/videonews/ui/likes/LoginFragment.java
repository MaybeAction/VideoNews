package com.example.maybe.videonews.ui.likes;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import com.example.maybe.videonews.R;
import com.example.maybe.videonews.bombapi.BombClient;
import com.example.maybe.videonews.bombapi.result.UserResult;
import com.example.maybe.videonews.commons.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends DialogFragment {
    private Unbinder mUnbinder;

    @BindView(R.id.etUsername)
    TextInputEditText etUsername;
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //取消标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_login, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btnLogin)
    public void onClick(){
            String username=etUsername.getText().toString();
            String password=etPassword.getText().toString();
            //用户名和密码不能为空
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            ToastUtils.showShort(R.string.username_or_password_can_not_be_null);
            return;
        }
        Call call= BombClient.getInstance().login(username,password);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

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
                    Log.e("okhttp","username = " + userResult.getUsername());
                    Log.e("okhttp","updatedAt = " + userResult.getUpdatedAt());
                }else{
                    Log.e("okhttp","请求失败，响应码= "+response.code());
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
