package com.balu.servicebreifdemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.balu.servicebreifdemo.R;
import com.balu.servicebreifdemo.service.MyStartService;

public class StartServiceActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);
    }

    public void startService(View view) {
        intent = new Intent(this, MyStartService.class);
        startService(intent);
    }


    public void stopService(View view) {
        if(intent!=null) {
            stopService(intent);
        }
    }
}
