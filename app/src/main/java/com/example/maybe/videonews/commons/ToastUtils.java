package com.example.maybe.videonews.commons;

import android.content.Context;
import android.widget.Toast;

/**
 * 吐司工具类
 */

public class ToastUtils {
    private static Toast toast;
    private static Context context;

    /**
     * 初始化操作
     * @param context 得到整个App的context
     */
    public static void init(Context context){
        ToastUtils.context=context.getApplicationContext();
    }

    /**
     *
     *
     * @param msg   要吐司的字符串
     */
    public static void showShort(String msg){
        if(toast==null){
            toast=Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        }else{
            toast.setText(msg);
        }

        toast.show();
    }

    /**
     *
     * @param resId  要吐司的int类型的值
     */
    public static void showShort(int resId){
        showShort(context.getResources().getString(resId));
    }
}
