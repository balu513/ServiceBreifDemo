package com.balu.servicebreifdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyAidlService extends Service {
    public MyAidlService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
