package com.example.displaywindowdemo;

import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication mApplication;

    // 这里必须写成public，不然会报错的
    public MyApplication() {}

    public static MyApplication getInstance() {
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}