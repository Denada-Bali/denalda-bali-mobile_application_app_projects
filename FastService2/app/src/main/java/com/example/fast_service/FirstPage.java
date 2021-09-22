package com.example.fast_service;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FirstPage extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView ;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        Fragment selected_fragment =  new LocationFragment();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frame_Layout1);

        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_Layout1,selected_fragment).commit();
        }


    private final BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selected_fragment = null;
            switch (item.getItemId()) {
                case R.id.Location:
                    selected_fragment = new LocationFragment();

                    break;

                case R.id.GiftCard:
                    selected_fragment = new GiftCartFragment();
                    break;

                case R.id.MyProfile:
                    selected_fragment = new ProfilFragment();
                    break;
                default:
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frame_Layout1, selected_fragment ).commit();

            return true;
        }
    };
 }
