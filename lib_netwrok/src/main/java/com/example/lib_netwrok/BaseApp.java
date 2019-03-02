package com.example.lib_netwrok;

import android.app.Application;
import android.content.Context;

public class BaseApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

    }
    public static Context getContext() {
        return context;
    }
}
