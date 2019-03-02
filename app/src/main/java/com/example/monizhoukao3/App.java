package com.example.monizhoukao3;

import com.example.lib_netwrok.BaseApp;
import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
