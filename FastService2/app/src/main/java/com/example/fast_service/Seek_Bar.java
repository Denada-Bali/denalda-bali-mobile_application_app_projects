package com.example.fast_service;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class Seek_Bar extends AppCompatActivity {

    private int seekBar1, seekBar2, seekBar3;
    private SeekBar seekB1, seekB2, seekB3;
    LinearLayout mySeekBar;
    private SwitchCompat aSwitch;
    private TextView texV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       //check condition
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
           //When night mode is equal to yes
          //Set dark theme
            setTheme(R.style.Theme_Dark);
        }else{
            //When night mode is equal to no
            //Set light theme
            setTheme(R.style.Theme_Light);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek__bar);

        init();
        initlistners();
/*
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            aSwitch.setChecked(true);
            aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(isChecked){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                  texV.setText("Dark Mode");
                        reset();
                    } else{
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        texV.setText("Light Mode");
                        reset();
                    }

                }
            });
        }
    */}

    private void reset() {

        Intent intent= new Intent(getApplicationContext(), Seek_Bar.class);
        startActivity(intent);
        finish();
    }

    public void  init(){

        mySeekBar = (LinearLayout) findViewById(R.id.mySeekBar);
        seekB1 = (SeekBar) findViewById(R.id.seek1);
        seekB2 = (SeekBar) findViewById(R.id.seek2);
        seekB3 = (SeekBar) findViewById(R.id.seek3);
        aSwitch= (SwitchCompat) findViewById(R.id.mode);
       // texV= (TextView) findViewById(R.id.t1);
    }

    private void initlistners() {

        seekB1.setOnSeekBarChangeListener(seekBarChangeListener);
        seekB2.setOnSeekBarChangeListener(seekBarChangeListener);
        seekB3.setOnSeekBarChangeListener(seekBarChangeListener);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //set night mode, when switch button is checked
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else {
                    //when switch button is unchecked
                    // set light mode
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

    }

    private  SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            BackgroundColorUpdate();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void BackgroundColorUpdate(){
        seekBar1 = seekB1.getProgress();
        seekBar2 = seekB2.getProgress();
        seekBar3 = seekB3.getProgress();

        mySeekBar.setBackgroundColor(
                0xff000000
                + seekBar1 * 0x10000
                + seekBar2 * 0*100
                + seekBar3
        );
    }
}