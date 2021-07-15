package com.example.displaywindowdemo.floatwindows;

public abstract class Factory {
    public abstract <T extends BaseFloatWindow> T createFloatWindow(Class<T> clz);
}
