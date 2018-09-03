package com.lidiwo.weexdemo;

import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/8/27 16:47
 * @Company：深圳思创远大企业管理咨询有限公司
 * @Description： *****************************************************
 */
public class ImageAdapter implements IWXImgLoaderAdapter {

    @Override
    public void setImage(String url, ImageView imageView, WXImageQuality wxImageQuality, WXImageStrategy wxImageStrategy) {

        if(!TextUtils.isEmpty(url)){
            if(url.startsWith("drawable://")){
                String imageName=url.substring("drawable://".length(),url.length()-4);
                int imageId = imageView.getContext().getResources().getIdentifier(imageName,"drawable", "com.lidiwo.weexdemo");
                imageView.setImageResource(imageId );
            }else{
                Glide.with(MyApplication.getContext()).load(url).into(imageView);
            }
        }


    }
}
