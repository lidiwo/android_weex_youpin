package com.lidiwo.weexdemo;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.bindingx.plugin.weex.BindingX;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.common.WXException;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/8/27 16:46
 * @Company：深圳思创远大企业管理咨询有限公司
 * @Description： *****************************************************
 */
public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        InitConfig config=new InitConfig.Builder().setImgAdapter(new ImageAdapter()).build();
        WXSDKEngine.initialize(this,config);
        try{
            WXSDKEngine.registerModule("MyModule", MyModule.class);
            BindingX.register();
        }catch (WXException e){
            e.printStackTrace();
        }
    }

    public static Context getContext(){
        return  mContext;
    }
}
