package com.example.displaywindowdemo.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.core.app.NotificationCompat;

import com.example.displaywindowdemo.MainActivity;
import com.example.displaywindowdemo.MyApplication;
import com.example.displaywindowdemo.R;
import com.example.displaywindowdemo.bean.CountdownContent;
import com.example.displaywindowdemo.bean.NoticeContent;
import com.example.displaywindowdemo.floatwindows.BaseFloatWindow;
import com.example.displaywindowdemo.floatwindows.CountdownFloatWindow;
import com.example.displaywindowdemo.floatwindows.EmergencyFloatWindow;
import com.example.displaywindowdemo.floatwindows.ExamPlanFloatWindow;
import com.example.displaywindowdemo.floatwindows.FloatWindowFactory;
import com.example.displaywindowdemo.view.FloatWindowListener;


public class DisplayService extends Service {
    private Context mContext;
    String channelID = "SysWindow_ChannelID_1";
    String channelName = "SysWindow";
    private WindowManager mWindowManager;
    private View mRootView;
    private Handler mHandler;

    public DisplayService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = MyApplication.getInstance();
        /**
         *创建Notification
         */
        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android 8.0 通知后系统界面崩溃 无限闪屏 --- 原因 ：oreo 引入的自适应图标  解决方法：删除res/mipmap-anydpi-v26
            Intent intent = new Intent(mContext, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity
                    (mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification;
            NotificationChannel mChannel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            mChannel.setSound(null, null);
            if (manager != null) {
                manager.createNotificationChannel(mChannel);
            }
            notification = new Notification.Builder(mContext, channelID)
                    .setChannelId(channelID)
                    .setContentTitle(getResources().getString(R.string.app_name))
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher).build();
            startForeground(1, notification);


            NoticeContent noticeContent = NoticeContent.getInstance();
            CountdownContent countdownContent = CountdownContent.getInstance();
            noticeContent.setMessage("111111111111111111111111111111111");
            countdownContent.setCountdownContent("距离xxx还有");
            countdownContent.setCountdownTime(20);
            BaseFloatWindow emergencyFloatWindow = FloatWindowFactory.getInstance().createFloatWindow(EmergencyFloatWindow.class);
            BaseFloatWindow countdownFloatWindow = FloatWindowFactory.getInstance().createFloatWindow(CountdownFloatWindow.class);
            BaseFloatWindow examPlanFloatWindow = FloatWindowFactory.getInstance().createFloatWindow(ExamPlanFloatWindow.class);
            ((EmergencyFloatWindow) emergencyFloatWindow).showFloatWindow();
            ((CountdownFloatWindow) countdownFloatWindow).showFloatWindow();
            emergencyFloatWindow.setFloatWindowListener(new FloatWindowListener() {
                @Override
                public void onClose() {
                    ((EmergencyFloatWindow) emergencyFloatWindow).removeFloatWindow();
                }

                @Override
                public void onClick() {

                }
            });

            countdownFloatWindow.setFloatWindowListener(new FloatWindowListener() {
                @Override
                public void onClose() {
                    ((CountdownFloatWindow) countdownFloatWindow).removeFloatWindow();
                }

                @Override
                public void onClick() {

                }
            });
//            FloatWindowManager.getInstance().showFloatWindow(mContext, FloatWindowFactory.FLOATWINDOW_EXAM_PLAN);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /*mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                removeFloatWindow();
            }
        }, 10 * 1000);*/
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    private void showFloatWindow() {
        String notice = "77777777777777777777777777777";

    }

    private void removeFloatWindow() {
        mWindowManager.removeViewImmediate(mRootView);
    }
}