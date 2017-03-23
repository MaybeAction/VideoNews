package com.example.maybe.videonews.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


/**
 * 基础Activity
 */
public class MyBaseActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private static final int START_ACTIVITY = 0;
    private static final int START_FORRESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * @param str 吐司字符串
     */
    protected void showToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param cls 跳转至目标class
     */
    protected void toClass(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }


    /**
     * @param cls         跳转至目标class 可以得到上个界面的返回值
     * @param requestCode 请求码
     */
    protected void toClassForResult(Class cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }


    /**
     * @param cls         目标class
     * @param bundle      需要传递的Bundle
     * @param intentModel 是否需要返回值
     * @param requestCode 请求码
     */
    protected void toClassWithBundle(Class cls, Bundle bundle, int intentModel, int requestCode) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        switch (intentModel) {
            case START_ACTIVITY:
                startActivity(intent);
                break;
            case START_FORRESULT:
                startActivityForResult(intent, requestCode);
                break;
            default:
                Toast.makeText(this, "无跳转模式", Toast.LENGTH_SHORT).show();
                break;
        }

    }

}
