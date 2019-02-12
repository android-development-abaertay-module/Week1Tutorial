package com.bodovix.week1tutorial.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class DeliveredSMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // show delivery confirmation message
        Toast.makeText(context,"SMS Delivered: receiver hit",Toast.LENGTH_SHORT).show();
        Log.d("gwyd","SMS DELIVERED");
    }
}
