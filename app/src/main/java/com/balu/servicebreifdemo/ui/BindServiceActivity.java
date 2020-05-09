package com.balu.servicebreifdemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import com.balu.servicebreifdemo.R;
import com.balu.servicebreifdemo.service.MyBindService;
import com.balu.servicebreifdemo.service.MyStartService;

public class BindServiceActivity extends AppCompatActivity {

    private ServiceConnection serviceConnection;
    private MyBindService myService;
    private Intent inttent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);

        serviceConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyBindService.MyBinder binder = (MyBindService.MyBinder) service;
                myService = ((MyBindService.MyBinder) service).getService();
                myService.setListner(BindServiceActivity.this);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                myService = null;
            }
        };
    }


    public void bindService(View view) {
        inttent = new Intent(this,MyBindService.class);
        bindService(inttent,serviceConnection,BIND_AUTO_CREATE);
        startService(inttent);
    }

    public void unBindService(View view) {
     unbindService(serviceConnection);
     stopService(inttent);
    }

    public void sendActivityToService(View view) {
       myService.sendData("Activity Data");
    }

    public void serviceToActivity(View view) {
      myService.requestData();
    }

    public void dataFromService(String value) {
        Toast.makeText(getApplicationContext(),value +" received into Activity",Toast.LENGTH_SHORT).show();
    }
}
