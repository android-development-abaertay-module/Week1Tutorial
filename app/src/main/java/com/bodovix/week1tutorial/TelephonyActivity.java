package com.bodovix.week1tutorial;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TelephonyActivity extends AppCompatActivity {

    private static final int READ_PHONE_STATE_REQUEST = 0;
    TelephonyManager telephonyManager;
    PhoneStateListener phoneStateListener;
    TextView countryCodeTV;
    TextView operatorTV;
    TextView networkNameTV;
    TextView deviceIdTV;
    TextView osVersionTV;
    TextView phoneNumberTV;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephony);

        checkPermissions();

        phoneStateListener = new PhoneStateListener(){
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);
                switch (state){
                    case TelephonyManager.CALL_STATE_IDLE:
                        Log.d("gwydion","Idle");
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:
                        Log.d("gwydion","RINGING");

                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        Log.d("gwydion","OFF Hook");

                        break;
                }
            }
        };
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        telephonyManager.listen(phoneStateListener,PhoneStateListener.LISTEN_CALL_STATE);

        countryCodeTV = findViewById(R.id.countryCodeTV);
        operatorTV = findViewById(R.id.operatorTV);
        networkNameTV = findViewById(R.id.networkNameTV);
        deviceIdTV = findViewById(R.id.imeNumberTV);
        osVersionTV = findViewById(R.id.osVersionTV);
        phoneNumberTV = findViewById(R.id.phoneNumberTV);

        btn1 = findViewById(R.id.btnOne);


        showNetworkDetails();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("gwydion","pause");
        telephonyManager.listen(phoneStateListener,PhoneStateListener.LISTEN_NONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("gwydion","resume");
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    READ_PHONE_STATE_REQUEST);

        } else {
            //permissions already granted
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_PHONE_STATE_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("gs", "granted");
                //permissions granted
            } else {
                Intent intent = new Intent(TelephonyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    private void showNetworkDetails() {
        countryCodeTV.setText(telephonyManager.getNetworkCountryIso());
        operatorTV.setText(telephonyManager.getNetworkOperator());
        networkNameTV.setText(telephonyManager.getNetworkOperatorName());
        try {
            deviceIdTV.setText(telephonyManager.getImei());
        } catch (SecurityException e) {
            deviceIdTV.setText("Permission Required");
        }
        catch (Exception e) {
            deviceIdTV.setText("Permission Required");
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        phoneNumberTV.setText(String.format("%s Phone number", telephonyManager.getLine1Number()));
    }

}
