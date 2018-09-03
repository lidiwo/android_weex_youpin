package com.lidiwo.weexdemo;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;

import java.lang.reflect.Method;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/8/28 13:16
 * @Company：深圳思创远大企业管理咨询有限公司
 * @Description： *****************************************************
 */
public class MyModule extends WXModule {

    @JSMethod(uiThread = false)
    public int getStatusBarHeight() {
        int statusBarHeight = 0;
        //获取status_bar_height资源的ID
        int resourceId = MyApplication.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = MyApplication.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        Log.e("@@@@@", "###@@@@@::" + statusBarHeight);

        return statusBarHeight;
    }


    @JSMethod(uiThread = false)
    public int getNavigationHeight() {
        int vh = 0;
        WindowManager windowManager = (WindowManager) MyApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            vh = dm.heightPixels - display.getHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vh;
    }


}
