package com.bodovix.week1tutorial;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SMSActivity extends AppCompatActivity {

    private static final int SEND_SMS_REQUEST = 0;

    EditText smsText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        smsText = findViewById(R.id.SMSTxt);
        checkPermissions();

        //Dynamically registered Receiver on create
        registerReceiver(new BroadcastReceiver() {
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
        }, new IntentFilter("SENT_SMS_ACTION") );
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
            // show delivery confirmation message
                Toast.makeText(context,"SMS Delivered: receiver hit",Toast.LENGTH_SHORT).show();
                Log.d("gwyd","SMS DELIVERED");
            }
        }, new IntentFilter("DELIVERED_SMS_ACTION"));

    }

    private void checkPermissions() {
        //check if sms permission needs to be approved
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    SEND_SMS_REQUEST);

        } else {
            //permissions already granted
            Log.d("gwyd","send sms permistion already granted");

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case SEND_SMS_REQUEST:
                Log.d("gwyd", "onRequestPermissionsResult: ");
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("gwyd", "granted");
                    //permissions granted
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(SMSActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    public void sendBtn_Clicked(View view) {
        //any request code
        int requestCode = 1;
        int requestCodeTwo = 2;
        String message = smsText.getText().toString();
        String number = "5556";

        Intent sentIntent = new Intent("SENT_SMS_ACTION");
        Intent deliverdIntent = new Intent("DELIVERED_SMS_ACTION");
        PendingIntent sentPI = PendingIntent.getBroadcast(getApplicationContext(), requestCode, sentIntent, 0);
        PendingIntent deliveryPI = PendingIntent.getBroadcast(getApplicationContext(),requestCodeTwo,deliverdIntent,0);

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, message, sentPI, deliveryPI);
        Log.d("gwyd","BTN CLICKED");

    }
}
