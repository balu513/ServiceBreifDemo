package com.balu.servicebreifdemo.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadCastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,
                intent.getStringExtra("com.balu.servicebreifdemo.service.extra.PARAM1"),Toast.LENGTH_SHORT).show();
    }
}
