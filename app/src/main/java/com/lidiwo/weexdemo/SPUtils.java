package com.lidiwo.weexdemo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2017/6/8 16:24
 * @Company：智能程序员
 * @Description： SharedPreferences工具
 * *****************************************************
 */
public class SPUtils {
    public  static final String SCANHISTORY="ScanHistory";
    private static final String SPFILENAME="lidiwo_weex";

    /**
     * @param context
     * @param key
     * 		关键字
     * @param value
     * 		值
     */
    public static void putBoolean(Context context, String key, boolean value){
        SharedPreferences sp = context.getSharedPreferences(SPFILENAME, Context.MODE_PRIVATE);
        //添加保存数据
        sp.edit().putBoolean(key, value).commit();

    }

    public static boolean getBoolean(Context context, String key, boolean defValue){
        SharedPreferences sp = context.getSharedPreferences(SPFILENAME, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);

    }

    public static void putString(Context context, String key, String value){
        SharedPreferences sp = context.getSharedPreferences(SPFILENAME, Context.MODE_PRIVATE);
        //添加保存数据
        sp.edit().putString(key, value).commit();

    }

    public static String getString(Context context, String key, String defValue){
        SharedPreferences sp = context.getSharedPreferences(SPFILENAME, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putInt(Context context, String key, int value){
        SharedPreferences sp = context.getSharedPreferences(SPFILENAME, Context.MODE_PRIVATE);
        //添加保存数据
        sp.edit().putInt(key, value).commit();

    }

    public static int getInt(Context context, String key, int defValue){
        SharedPreferences sp = context.getSharedPreferences(SPFILENAME, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);

    }
    public static void putLong(Context context, String key, long value){
        SharedPreferences sp = context.getSharedPreferences(SPFILENAME, Context.MODE_PRIVATE);
        //添加保存数据
        sp.edit().putLong(key, value).commit();

    }

    public static long getLong(Context context, String key, Long defValue){
        SharedPreferences sp = context.getSharedPreferences(SPFILENAME, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }


    public static void clear(Context context){
        SharedPreferences sp = context.getSharedPreferences(SPFILENAME, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

}
