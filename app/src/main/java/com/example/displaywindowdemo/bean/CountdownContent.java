package com.example.displaywindowdemo.bean;

public class CountdownContent {
    private static CountdownContent instance;
    String countdownContent;
    int countdownTime;

    private CountdownContent() {
    }

    private CountdownContent(String countdownContent, int countdownTime) {
        this.countdownContent = countdownContent;
        this.countdownTime = countdownTime;
    }
    public static CountdownContent getInstance(){
        if (instance == null) {
            instance = new CountdownContent();
        }
        return instance;
    }



    public String getCountdownContent() {
        return countdownContent;
    }

    public void setCountdownContent(String countdownContent) {
        this.countdownContent = countdownContent;
    }

    public int getCountdownTime() {
        return countdownTime;
    }

    public void setCountdownTime(int countdownTime) {
        this.countdownTime = countdownTime;
    }
}
