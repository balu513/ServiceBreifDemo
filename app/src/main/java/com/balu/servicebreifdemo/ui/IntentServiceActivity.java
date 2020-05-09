package com.balu.servicebreifdemo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.balu.servicebreifdemo.R;
import com.balu.servicebreifdemo.broadcast_receiver.MyBroadCastReceiver;
import com.balu.servicebreifdemo.service.MyIntentService;

public class IntentServiceActivity extends AppCompatActivity {

    private MyBroadCastReceiver receiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        receiver = new MyBroadCastReceiver();
        intentFilter = new IntentFilter("myAction");
    }

    public void startIntentService(View view) {
        new MyIntentService().startActionFoo(this,"BALU","BALLI");
    }

    public void stopIntentService(View view) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}
