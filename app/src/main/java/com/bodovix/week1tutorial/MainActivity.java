package com.bodovix.week1tutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    TextView textInput;
    String enteredNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInput = findViewById(R.id.textInput);
        enteredNumber = "";
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
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
