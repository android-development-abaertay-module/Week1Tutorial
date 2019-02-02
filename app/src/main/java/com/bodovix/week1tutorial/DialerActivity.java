package com.bodovix.week1tutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DialerActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    TextView textInput;
    String enteredNumber;
    GestureDetector gestureScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer);

        textInput = findViewById(R.id.textInput);
        textInput.setOnTouchListener(this);

        enteredNumber = "";
        gestureScanner = new GestureDetector(getApplicationContext(),this);
    }

    public void callBtn_Clicked(View view){
        if (enteredNumber.length() > 0) {
            Toast.makeText(getApplicationContext(),"Calling: " + textInput.getText(),
                    Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Text Input Empty",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void btn_Clicked(View view){
        Button btnClicked = (Button) view;
        String tag = btnClicked.getTag().toString();

        enteredNumber = enteredNumber + tag;
        textInput.setText(enteredNumber);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d("Touch", "onShowPress hit");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("Touch", "onSingleTapUp hit");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d("Touch", "onScroll hit");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("Touch", "onPress hit");

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("Touch", "onFling hit");

        enteredNumber = "";
        textInput.setText("");
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("Touch", "onTouch hit");

        switch (v.getId()){
            case R.id.textInput:
                Log.d("Touch", "textInput  hit");

                gestureScanner.onTouchEvent(event);
            break;
        }
        return true;
    }
}


