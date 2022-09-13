package com.example;

import android.app.Application;
import android.util.Log;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MainApplication", "MainApplication onCreate");
    }
}
