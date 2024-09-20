package com.example.p18;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import android.content.BroadcastReceiver;

public class MyBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, "Custom intent Received", Toast.LENGTH_SHORT).show();
    }
}
