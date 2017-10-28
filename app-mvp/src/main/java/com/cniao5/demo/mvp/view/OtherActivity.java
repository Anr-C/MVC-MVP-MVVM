package com.cniao5.demo.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cniao5.demo.mvp.R;


public class OtherActivity extends AppCompatActivity implements OtherBaseView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);


        //业务逻辑相关        请求图片或下载上传。。。
        //UI相关的业务逻辑    显示图片  显示进度条  弹TOAST，，，，

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showProgress(int progress) {

    }
}
