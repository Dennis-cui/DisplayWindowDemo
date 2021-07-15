package com.example.displaywindowdemo.bean;

public class NoticeContent {
    private static NoticeContent instance;
    String message;
    int displayTime;

    public int getTime() {
        return displayTime;
    }

    public void setTime(int time) {
        this.displayTime = time;
    }

    private NoticeContent() {
    }

    private NoticeContent(String message) {
        this.message = message;
    }

    public static NoticeContent getInstance() {
        if (instance == null) {
            instance = new NoticeContent();
        }
        return instance;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
