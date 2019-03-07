package com.execti.whasee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class SmActivity extends AppCompatActivity implements QRCodeView.Delegate {
    private ZXingView xingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm);
        //获取资源id
        xingView = findViewById(R.id.zxing);
        xingView.setDelegate(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        xingView.startCamera();
        xingView.startSpotAndShowRect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        xingView.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
