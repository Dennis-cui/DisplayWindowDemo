package com.example.displaywindowdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.displaywindowdemo.permission.FloatWinPermissionCompat;
import com.example.displaywindowdemo.service.DisplayService;

public class MainActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        checkPermissionAndShow();
    }

    public void onStartService(View view) {
        Intent intent = new Intent(this, DisplayService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        }
    }

    private void checkPermissionAndShow() {
        // 检查是否已经授权
        if (FloatWinPermissionCompat.getInstance().check(mContext)) {
            //showFloatWindowDelay();
        } else {
            // 授权提示
            new AlertDialog.Builder(mContext).setTitle("悬浮窗权限未开启")
                    .setMessage("你的手机没有授权" + mContext.getString(R.string.app_name) + "获得悬浮窗权限，视频悬浮窗功能将无法正常使用")
                    .setPositiveButton("开启", (dialog, which) -> {
                        // 显示授权界面
                        try {
                            FloatWinPermissionCompat.getInstance().apply(mContext);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    })
                    .setNegativeButton("取消", null).show();
        }
    }
}