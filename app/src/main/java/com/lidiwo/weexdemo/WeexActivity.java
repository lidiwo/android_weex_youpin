package com.lidiwo.weexdemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/8/27 16:48
 * @Company：深圳思创远大企业管理咨询有限公司
 * @Description： *****************************************************
 */
public class WeexActivity extends AppCompatActivity implements IWXRenderListener {

    private WXSDKInstance mWXSDKInstance;
    private ImmersionBar mImmersionBar;
    private FrameLayout fl_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weex);
        fl_content = findViewById(R.id.fl_content);
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.statusBarDarkFont(true).fitsSystemWindows(true) .init();
        mWXSDKInstance = new WXSDKInstance(this);
        mWXSDKInstance.registerRenderListener(this);
        /**
         * WXSample 可以替换成自定义的字符串，针对埋点有效。
         * template 是.we transform 后的 js文件。
         * option 可以为空，或者通过option传入 js需要的参数。例如bundle js的地址等。
         * jsonInitData 可以为空。
         */

        String weex_url = getIntent().getStringExtra("weex_url");
        if (TextUtils.isEmpty(weex_url)) {
            mWXSDKInstance.render("WXSample", WXFileUtils.loadAsset("index.js", this), null, null, WXRenderStrategy.APPEND_ASYNC);
        } else {
            String originalUrl = getOriginalurl(weex_url);
            Map<String, Object> options = new HashMap<>();
            options.put(WXSDKInstance.BUNDLE_URL, originalUrl);
            mWXSDKInstance.setTrackComponent(true);
            mWXSDKInstance.renderByUrl("WXSample", originalUrl, options, null, WXRenderStrategy.APPEND_ASYNC);
        }
    }

    private String getOriginalurl(String url) {
        Uri uri = Uri.parse(url);
        Set<String> urlParameter = uri.getQueryParameterNames();
        if (urlParameter != null && urlParameter.size() > 0 && urlParameter.contains("_wx_tpl")) {
            String originalUrl = uri.getQueryParameter("_wx_tpl");
            if (!TextUtils.isEmpty(originalUrl)) {
                return originalUrl;
            }
        }
        return url;
    }


    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        if(fl_content!=null){
            fl_content.removeAllViews();
        }
        fl_content.addView(view);
      //  setContentView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {
    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        Log.e("@@@@@", "3" + instance);
        Log.e("@@@@@", "3" + errCode);
        Log.e("@@@@@", "3" + msg);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
        if (mWXSDKInstance != null) {
            mWXSDKInstance.onActivityDestroy();
        }
    }
}

