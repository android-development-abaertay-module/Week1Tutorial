package com.bodovix.week1tutorial.View;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bodovix.week1tutorial.R;
import com.bodovix.week1tutorial.ViewModel.DialerViewModel;

public class DialerActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {

    TextView textInput;
    DialerViewModel VM;
    //live data used instead of normal string to communicate with VM
    LiveData<String> enteredNumberLD;

    GestureDetector gestureScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer);

        textInput = findViewById(R.id.textInput);
        textInput.setOnTouchListener(this);

        VM = ViewModelProviders.of(this).get(DialerViewModel.class);
        enteredNumberLD = VM.getNumberEntered();
        enteredNumberLD.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String  value) {
                //when VM parameter changes. update the view
                textInput.setText(value);
            }
        });

        gestureScanner = new GestureDetector(getApplicationContext(),this);
    }

    public void callBtn_Clicked(View view){
        if (enteredNumberLD.getValue().length() > 0) {
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

        String enteredNumber = enteredNumberLD.getValue() + tag;
        VM.setNumberEntered(enteredNumber);
        //View updated via Observer attached to VM Parameter
        //textInput.setText(enteredNumber);
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

        //clear entered Number
        VM.setNumberEntered("");
        //updated via vm
        //textInput.setText(enteredNumber);
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("Touch", "onTouch hit");

        switch (v.getId()){
            case R.id.textInput:
                //when text input touched watch gesture Scanner
                Log.d("Touch", "textInput  hit");

                gestureScanner.onTouchEvent(event);
            break;
        }
        return true;
    }
}


