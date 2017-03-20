package com.example.maybe.videonews.bombapi;

import com.example.maybe.videonews.bombapi.entity.UserEntity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * OKHttp解析类
 */

public class BombClient {
    private static BombClient bombClient;
    private OkHttpClient okHttpClient;
    private static final String USERNAME="username";
    private static final String PASSWORD="password";

    public static BombClient getInstance(){
            if(bombClient==null){
                bombClient=new BombClient();
            }
        return bombClient;
    }

    private BombClient() {
        //构建“日志拦截器”
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        //设置“日志拦截器”的拦截级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient=new OkHttpClient.Builder()
                //添加Bomb必要的头字段的拦截器
                .addInterceptor(new BombIntercepted())
                //添加日志拦截器
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    /**
     * 注册OKHttp解析
     * @param username  用户名
     * @param password  密码
     * @return          返回一个Call对象
     */
    public Call register(String username,String password) {
//        //构建一个请求的请求体（根据服务器要求）
//        JSONObject jsonObject=new JSONObject();
//        try {
//            jsonObject.put(USERNAME,username);
//            jsonObject.put(PASSWORD,password);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String json=jsonObject.toString();
//        RequestBody requestBody=RequestBody.create(null,json);
//        Request request=new Request.Builder()
//                .url(BombConst.mainURL+"1/users")
//                .post(requestBody)
//                .build();

        //构建一个请求的请求体（根据服务器要求）
        //Gson是一个用来生成，解析Json数据的第三方库
        //生成，可以将一个类，生成为一串json格式的数据
        String json=new Gson().toJson(new UserEntity(username,password));

        RequestBody requestBody=RequestBody.create(null,json);

        Request request=new Request.Builder()
                .url(BombConst.mainURL+"1/users")
                .post(requestBody)
                .build();
            return okHttpClient.newCall(request);
    }

    /**
     * 登录OKhttp解析
     * @param username  用户名
     * @param password  密码
     * @return          返回一个Call对象
     */
    public Call login(String username,String password){
            Request request=new Request.Builder()
                    .get()
                    .url(BombConst.mainURL+"1/login" + "?"
                            +"username=" + username + "&"
                            +"password=" + password)
                    .build();
            return okHttpClient.newCall(request);
    }



}
