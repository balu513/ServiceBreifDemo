package com.balu.servicebreifdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import com.balu.servicebreifdemo.ui.BindServiceActivity;

public class MyBindService extends Service {
    IBinder binder =  new MyBinder();
    private BindServiceActivity activity;

    private String value = "Service DATA";

    public MyBindService() {
    }

    public void sendData(String str) {
        Toast.makeText(getApplicationContext(),str+" received in Service",Toast.LENGTH_SHORT).show();
    }

    public void setListner(BindServiceActivity activity) {
        this.activity = activity;
    }

    public void requestData() {
        activity.dataFromService(value);
    }

    public class MyBinder extends Binder {
        public MyBindService getService(){
          return MyBindService.this;
      }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
