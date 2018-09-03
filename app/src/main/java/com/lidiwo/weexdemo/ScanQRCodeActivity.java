package com.lidiwo.weexdemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2018/8/27 13:46
 * @Company：深圳思创远大企业管理咨询有限公司
 * @Description： *****************************************************
 */
public class ScanQRCodeActivity extends AppCompatActivity   implements QRCodeView.Delegate,View.OnClickListener{

    private ZBarView zbv_scan;
    private ImageView tv_back;
    private ImageView iv_history;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_scan_qrcode);
        zbv_scan = findViewById(R.id.zbv_scan);
        zbv_scan.setDelegate(this);
        tv_back = findViewById(R.id.tv_back);
        iv_history = findViewById(R.id.iv_history);
        tv_back.setOnClickListener(this);
        iv_history.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        zbv_scan.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZBarView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别
        zbv_scan.startSpotAndShowRect(); // 显示扫描框，并且延迟0.5秒后开始识别
    }

    @Override
    protected void onStop() {
        super.onStop();
        zbv_scan.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        zbv_scan.onDestroy(); // 销毁二维码扫描控件
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        //保存扫描结果
        if(!TextUtils.isEmpty(result)){
            Intent intent = new Intent(ScanQRCodeActivity.this, WeexActivity.class);
            intent.putExtra("weex_url", result);
            startActivity(intent);

            String scanHistory=SPUtils.getString(this,SPUtils.SCANHISTORY,"");
            if(TextUtils.isEmpty(scanHistory)){
                SPUtils.putString(this,SPUtils.SCANHISTORY,result);
            }else{
                StringBuilder sb=new StringBuilder();
                sb.append(result);
                sb.append("@");
                sb.append(scanHistory);
                SPUtils.putString(this,SPUtils.SCANHISTORY,sb.toString());
            }
        }
        vibrate();//震动
        zbv_scan.startSpot(); // 延迟0.5秒后开始识别
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(this,"打开相机出错",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_back:
                finish();
                break;
            case R.id.iv_history:
                startActivity(new Intent(this,ScanHistoryActivity.class));
                break;
        }
    }
}
