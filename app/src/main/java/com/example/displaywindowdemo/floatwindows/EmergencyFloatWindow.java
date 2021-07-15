package com.example.displaywindowdemo.floatwindows;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.displaywindowdemo.MyApplication;
import com.example.displaywindowdemo.R;
import com.example.displaywindowdemo.bean.NoticeContent;
import com.example.displaywindowdemo.utils.SystemUtils;

public class EmergencyFloatWindow extends BaseFloatWindow {

    public EmergencyFloatWindow() {
        this(MyApplication.getInstance());
    }

    public EmergencyFloatWindow(Context context) {
        super(context);
        Log.d("jianchao", "EmergencyFloatWindow: ");
    }

    @Override
    protected void setWindowPositionAndSize(Context context) {
        Log.d("jianchao", "setWindowPositionAndSize: ");
        mLayoutParams.width = SystemUtils.getScreenWidth(context);
        Log.d("jianchao", "mLayoutParams.width: " + mLayoutParams.width);
        mLayoutParams.height = 200;
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;
    }

    @Override
    protected int getLayoutId() {
        Log.d("jianchao", "getLayoutId: ");
        return R.layout.float_view_emergency_layout;
    }

    @Override
    public void showFloatWindow() {
        NoticeContent noticeContent = NoticeContent.getInstance();
        String message = noticeContent.getMessage();
        TextView textView = mRootView.findViewById(R.id.tv_emergency_notice);
        ImageView close = mRootView.findViewById(R.id.iv_close_window);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFloatWindowListener.onClose();
            }
        });
        textView.setText(message);
        super.showFloatWindow();
    }

    @Override
    public void removeFloatWindow() {
        super.removeFloatWindow();
    }
}
