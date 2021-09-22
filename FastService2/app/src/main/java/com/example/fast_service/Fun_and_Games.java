package com.example.fast_service;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Fun_and_Games extends AppCompatActivity {

    private ImageView Wheel;
    private TextView Text, text_1;
    private Button PlayWheel;
    private  int counter = 1;


    String[] sectors = {"Try Again", "$ 5 ", "Try Again", "$ 1", "Try Again", "Try Again", "Try Again", "1 day off"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_and__games);

        init();
        initlistners();
    }

    private void init() {
        Wheel = (ImageView) findViewById(R.id.Wheel);
        Text = (TextView) findViewById(R.id.Text_1_);
        text_1 =(TextView) findViewById(R.id.text__1);
        PlayWheel = (Button) findViewById(R.id.PlayWheel);
    }


    private void initlistners() {
        PlayWheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.reverse(Arrays.asList(sectors));

                Random rr = new Random();
                int degree = rr.nextInt(360);

                RotateAnimation rotateAnimation= new RotateAnimation(0, degree + 720,
                        RotateAnimation.RELATIVE_TO_SELF,0.5f,
                        RotateAnimation.RELATIVE_TO_SELF,0.5f);

                rotateAnimation.setDuration(3000);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());

                rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        CalculatePoint(degree);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                Wheel.startAnimation(rotateAnimation);
            }
        });
    }

        private void CalculatePoint(int degree) {
            //total degree 360   || 8 segment || 45 degree each segment
            int initialPoint=0;
            int endPoint = 45;
            int i =0;
            String res = null;

            do{
                if (degree > initialPoint && degree < endPoint){
                    res = sectors[i];
                }
                initialPoint += 45; endPoint += 45;
                i++;
            }while (res == null);
            Text.setText(res);

            counter--;
            text_1.setText("Oportunity : " + String.valueOf(counter));
            if (counter == 0){
                PlayWheel.setEnabled(false);

            }
        }
    }