package com.example.fast_service;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class GiftCartFragment extends Fragment {
    View view;

private TextView Take_Order, games, myMenu;
private  int counter = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.gift_cart_fragment, container, false);
        init();
        initlistners();
   return view;
    }

    private void init() {
        Take_Order = (TextView) view.findViewById(R.id.Take_Order);
        games = (TextView) view.findViewById(R.id.games);
        myMenu = (TextView)view.findViewById(R.id.myMenu);
    }


    private void initlistners() {
        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getActivity(), Fun_and_Games.class);
                startActivity(startIntent);
            }
        });
        Take_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getActivity(), WaiterDashboard.class);
                startActivity(startIntent);
            }
        });
        myMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getActivity(), FoodListActivity.class);
                startActivity(startIntent);
            }
        });

    }
}