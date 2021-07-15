package com.example.displaywindowdemo.floatwindows;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.example.displaywindowdemo.view.FloatWindowListener;

public abstract class BaseFloatWindow implements IFloatWindow {

    private WindowManager mWindowManager;
    protected View mRootView;
    protected WindowManager.LayoutParams mLayoutParams;
    protected boolean mIsRemove = true;
    protected FloatWindowListener mFloatWindowListener;

    public BaseFloatWindow(Context context) {
        Log.d("jianchao", "BaseFloatWindow: ");
        initWindow(context);
    }

    private void initWindow(Context context) {
        Log.d("jianchao", "initWindow: ");
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mRootView = LayoutInflater.from(context).inflate(getLayoutId(), null);
        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.packageName = context.getPackageName();

        // 如果没有设置FLAG_NOT_FOCUSABLE，那么屏幕上弹窗之外的地方不能点击。
        // 如果设置了FLAG_NOT_FOCUSABLE，那么屏幕上弹窗之外的地方能够点击、但是弹窗上的EditText无法输入、键盘也不会弹出来。
        // 如果设置了FLAG_NOT_TOUCH_MODAL，那么屏幕上弹窗之外的地方能够点击、弹窗上的EditText也可以输入、键盘能够弹出来。
        // FLAG_KEEP_SCREEN_ON,当这个window对用户是可见状态,则保持设备屏幕不关闭且不变暗
        //
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SCALED
                | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        // 6.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        }
        // 透明
        mLayoutParams.format = PixelFormat.RGBA_8888;
        // 窗体的大小和位置通过WindowManager.LayoutParams来设置，在通过x和y值来设置窗体位置时，
        // 需要注意gravity属性，如果gravity没有设置或者是center之类的，那么设置的x和y值就不会起作用.
        mLayoutParams.gravity = Gravity.START | Gravity.TOP;

        setWindowPositionAndSize(context);
        Log.d("jianchao", "mLayoutParams.width: " + mLayoutParams.width);
        Log.d("jianchao", "mLayoutParams.height: " + mLayoutParams.height);
    }

    protected abstract void setWindowPositionAndSize(Context context);

    protected abstract int getLayoutId();

    public void showFloatWindow(float x, float y) {
        mLayoutParams.x = (int) x;
        mLayoutParams.y = (int) y;
        showFloatWindow();
    }

    protected void showFloatWindow() {
        if (mIsRemove) {
            mIsRemove = false;
            mWindowManager.addView(mRootView, mLayoutParams);
        }
    }

    protected void removeFloatWindow() {
        if (!mIsRemove) {
            mIsRemove = true;
            mWindowManager.removeViewImmediate(mRootView);
        }
    }

    @Override
    public void setFloatWindowListener(FloatWindowListener listener) {
        this.mFloatWindowListener = listener;
    }
}
