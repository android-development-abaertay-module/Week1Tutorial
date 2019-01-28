package com.bodovix.week1tutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
}
