package com.bodovix.week1tutorial.BroadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class CallInterceptor extends BroadcastReceiver {

    public CallInterceptor(){


    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        TelephonyManager telephony = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

        PhoneStateListener phoneStateListener = new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);
                switch (state){
                    case TelephonyManager.CALL_STATE_IDLE:
                        Log.d("Gwydion","Idle");
                        Toast.makeText(context,"State Changed to IDLE", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:
                        Log.d("Gwydion","RINGING: " + phoneNumber);
                        Toast.makeText(context,"State Changed to RINGING: " + phoneNumber, Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        Log.d("Gwydion","OFF HOOK");
                        Toast.makeText(context,"State Changed to OFF HOOK", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        telephony.listen(phoneStateListener,PhoneStateListener.LISTEN_CALL_STATE);
        Log.d("Gwydion","Broadcast Hit");

        if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)){
            String oldNumber = intent.getStringExtra(intent.EXTRA_PHONE_NUMBER);
            Log.e("OUTGOING", "To: "+ oldNumber);
            this.setResultData("0123456789");
        }
    }

}
