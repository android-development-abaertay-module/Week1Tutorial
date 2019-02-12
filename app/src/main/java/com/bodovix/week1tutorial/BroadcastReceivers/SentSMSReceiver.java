package com.bodovix.week1tutorial.BroadcastReceivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class SentSMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("gwyd","on Receive method hit");
        switch(getResultCode()){
            case Activity.RESULT_OK:
                //display SENT message
                Toast.makeText(context,"SMS SENT: receiver hit",Toast.LENGTH_SHORT).show();
                Log.d("gwyd","SMS SENT");
                break;
            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                // display ERROR message
                Toast.makeText(context,"SMS ERROR: receiver hit",Toast.LENGTH_SHORT).show();
                Log.d("gwyd","SMS ERROR");

                break;
        }
    }
}
