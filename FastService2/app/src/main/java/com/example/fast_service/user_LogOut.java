package com.example.fast_service;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class user_LogOut extends AppCompatActivity {

    private TextView exit, seekbar_Theme, share;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__log_out);

        init();
        initlistners();
    }

    public void  init(){
        exit = (TextView) findViewById(R.id.Exit1);
        seekbar_Theme =(TextView) findViewById(R.id.seekbar_Theme1);
        share =(TextView) findViewById(R.id.share1);

        mFirebaseAuth = FirebaseAuth.getInstance();

    }

    private void initlistners() {
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                share();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Exit();
            }
        });
        seekbar_Theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Seek_Bar.class);
                startActivity(startIntent);
            }
        });
    }

    private void share() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareBody= "Download this Application: http://play.google.com/store/apps/details?id=com.instagram.android";
        String sharesub="CCC Course App";

        shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody);
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);

        startActivity(Intent.createChooser(shareIntent,"Share Using"));
    }

    public void Exit(){
        mFirebaseAuth.signOut();
        startActivity(new Intent(user_LogOut.this, FirstActivity.class));
        finish();
    }

}