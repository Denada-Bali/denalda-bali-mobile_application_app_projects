package com.example.fast_service;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SecondPage extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView ;
    private FrameLayout frame_Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        Fragment selected_fragment =  new userLocationFragment();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView1);
        frame_Layout = findViewById(R.id.frame_Layout);

        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_Layout, selected_fragment).commit();
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selected_fragment = null;
            switch (item.getItemId()) {
                case R.id.userLocation:
                    selected_fragment = new userLocationFragment();
                    break;

                case R.id.userGiftCard:
                    selected_fragment = new userGiftCartFragment();
                    break;

                case R.id.userProfile:
                    selected_fragment = new userProfilFragment();
                    break;
                default:
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frame_Layout, selected_fragment).commit();

            return true;
        }
    };
}

