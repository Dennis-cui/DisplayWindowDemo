package com.example.displaywindowdemo.floatwindows;

import android.content.Context;
import android.util.Log;

public class FloatWindowFactory extends Factory {

    public static final int FLOATWINDOW_EMERGENCY = 11;
    public static final int FLOATWINDOW_COUNTDOWN = 12;
    public static final int FLOATWINDOW_EXAM_PLAN = 14;

    private FloatWindowFactory() {
    }

    public static FloatWindowFactory getInstance() {
        return NestClass.instance;
    }

    @Override
    public <T extends BaseFloatWindow> T createFloatWindow(Class<T> clz) {
        BaseFloatWindow baseFloatWindow = null;
        try {
            baseFloatWindow = (BaseFloatWindow) Class.forName(clz.getName()).newInstance();
            Log.d("jianchao", "createFloatWindow: ");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) baseFloatWindow;
    }

    private static class NestClass {
        private static FloatWindowFactory instance;

        static {
            instance = new FloatWindowFactory();
        }
    }
}