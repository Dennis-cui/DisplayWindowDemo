package com.example.displaywindowdemo.floatwindows;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.displaywindowdemo.MyApplication;
import com.example.displaywindowdemo.R;
import com.example.displaywindowdemo.bean.CountdownContent;

public class CountdownFloatWindow extends BaseFloatWindow {

    private Context mContext;
    private String mCountdownContent;

    public CountdownFloatWindow() {
        this(MyApplication.getInstance());
    }

    public CountdownFloatWindow(Context context) {
        super(context);

        Log.d("jianchao", "CountdownFloatWindow: ");
    }

    @Override
    protected void setWindowPositionAndSize(Context context) {
        Log.d("jianchao", "setWindowPositionAndSize: ");
        mLayoutParams.width = 200;
        Log.d("jianchao", "mLayoutParams.width: " + mLayoutParams.width);
        mLayoutParams.height = 200;
        mLayoutParams.x = 0;
        mLayoutParams.y = 200;
    }

    @Override
    protected int getLayoutId() {
        Log.d("jianchao", "getLayoutId: ");
        return R.layout.float_view_countdown_layout;
    }

    @Override
    public void showFloatWindow() {
        CountdownContent countdownContent = CountdownContent.getInstance();
        String content = countdownContent.getCountdownContent();
        int countdownTime = countdownContent.getCountdownTime();
        TextView tvContent = mRootView.findViewById(R.id.tv_countdown_content);
        TextView tvTime = mRootView.findViewById(R.id.tv_countdown_time);
        tvContent.setText(content);
        tvTime.setText(String.valueOf(countdownTime));
        ImageView close = mRootView.findViewById(R.id.iv_close_window);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFloatWindowListener.onClose();
            }
        });
        super.showFloatWindow();
    }

    @Override
    public void removeFloatWindow() {
        super.removeFloatWindow();
    }
}
