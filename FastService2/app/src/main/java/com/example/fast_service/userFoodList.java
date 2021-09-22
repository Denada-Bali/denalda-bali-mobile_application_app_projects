package com.example.fast_service;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class userFoodList extends AppCompatActivity {
    public WebView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_food_list);
        myView = (WebView) findViewById(R.id.myView1);
        myView.setWebViewClient(new WebViewClient());
        myView.loadUrl("https://www.foodiesfeed.com/");

        WebSettings webSettings =myView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if(myView.canGoBack()){
            myView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}