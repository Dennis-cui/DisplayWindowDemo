package com.example.displaywindowdemo.floatwindows;

import android.content.Context;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.displaywindowdemo.MyApplication;
import com.example.displaywindowdemo.R;
import com.example.displaywindowdemo.utils.SystemUtils;
import com.example.displaywindowdemo.view.FloatWindowListener;
import com.example.displaywindowdemo.view.MarqueeTextView;

public class ExamPlanFloatWindow extends BaseFloatWindow {

    public ExamPlanFloatWindow() {
        this(MyApplication.getInstance());
    }

    public ExamPlanFloatWindow(Context context) {
        super(context);

        Log.d("jianchao", "ExamPlanFloatWindow: ");
    }

    @Override
    protected void setWindowPositionAndSize(Context context) {
        Log.d("jianchao", "setWindowPositionAndSize: ");
        mLayoutParams.width = SystemUtils.getScreenWidth(context);
        Log.d("jianchao", "mLayoutParams.width: " + mLayoutParams.width);
        mLayoutParams.height = SystemUtils.getScreenHeight(context, true);
        mLayoutParams.x = 0;
        mLayoutParams.y = 0;
    }

    @Override
    protected int getLayoutId() {
        Log.d("jianchao", "getLayoutId: ");
        return R.layout.float_view_exam_plan_layout;
    }

    @Override
    public void showFloatWindow() {
        TextView textView = mRootView.findViewById(R.id.tv_exam_plan);
        textView.setText("这是期中考试计划：");
        super.showFloatWindow();
    }

    @Override
    public void removeFloatWindow() {
        super.removeFloatWindow();
    }
}
