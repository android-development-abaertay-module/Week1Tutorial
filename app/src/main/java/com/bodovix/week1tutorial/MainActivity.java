package com.bodovix.week1tutorial;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bodovix.week1tutorial.View.DialerActivity;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    Runnable runnable;

    Button dialerScreenViewBtn;
    TextView clockDisplayTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialerScreenViewBtn = findViewById(R.id.dialerScreenBtn);
        clockDisplayTxt = findViewById(R.id.clockTextView);

        handler = new Handler(){
            public void handleMessage (Message msg){
                String display = msg.getData().getString("display");
                clockDisplayTxt.setText(display);
            }
        };

        runnable = new Runnable() {
            @Override
            public void run() {
                updateTime();
            }
        };

        //start timer thread
        handler.postDelayed(runnable,1000);
    }

    private void updateTime() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        String hours = calendar.get(Calendar.HOUR_OF_DAY) + "";
        if (hours.length() == 1){
            hours = "0" +hours;
        }
        String minutes = calendar.get(Calendar.MINUTE) + "";
        if (minutes.length() == 1){
            minutes = "0" + minutes;
        }

        int seconds = calendar.get(Calendar.SECOND);
        boolean tick;
        if (seconds % 2 == 0){
            tick = true;
        }else{
            tick = false;
        }

        String display;
        if (tick){
            display = hours + ":" + minutes;
        }else{
            display = hours + " " + minutes;
        }

        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("display",display);

        message.setData(bundle);
        handler.sendMessage(message);
        handler.postDelayed(runnable,1000);
    }

    public void dialerScreenBtn_Click(View view) {
        Intent intent = new Intent(MainActivity.this, DialerActivity.class);
        startActivity(intent);

    }

    public void browserScreenBtn_Click(View view) {
        Intent intent = new Intent(MainActivity.this,BrowserActivity.class);
        startActivity(intent);
    }

    public void telephonyBtn_Click(View view) {
        Intent intent = new Intent(MainActivity.this, TelephonyActivity.class);
        startActivity(intent);
    }
}
