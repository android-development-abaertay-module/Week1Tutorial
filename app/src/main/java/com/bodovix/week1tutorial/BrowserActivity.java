package com.bodovix.week1tutorial;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BrowserActivity extends AppCompatActivity implements View.OnKeyListener {

    EditText searchTxt;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        searchTxt = findViewById(R.id.browserSearchTxt);
        searchTxt.setOnKeyListener(this);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        try{
            String inputURL = getIntent().getDataString().toString();
            if (inputURL != null && !inputURL.isEmpty()) {
                webView.loadUrl(inputURL);
                searchTxt.setText(inputURL);
            }
        }catch (Exception ex){
            //not loaded with uri (internal intent)
            //do nothing
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        int test = keyCode;
        //TODO: 67 = software enter key - need to figure out proper enum
        if (keyCode == KeyEvent.KEYCODE_ENTER ){
            browseWeb(searchTxt.getText().toString());
            return  true;
        }
        return false;
    }
    private void browseWeb(String address){
        if(address.contains("https://www.")) {
            webView.loadUrl(address);
        } else if(address.contains("www.")){
            webView.loadUrl("https://"+address);
        } else {
            webView.loadUrl("https://www."+address);
        }
    }
}


