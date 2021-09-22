package com.example.fast_service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyList extends BaseAdapter {
    Context context;
    String MENU[];
    int foodImages[];
   String price[];
    LayoutInflater inflter;

    public MyList(Context applicationContext, int[] foodImages, String[] MENU,  String[] price) {
        this.context = context;
        this.MENU = MENU;
        this.foodImages = foodImages;
       this.price = price;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return MENU.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflter.inflate(R.layout.activity_listview, null);

        ImageView FoodImage = (ImageView) view.findViewById(R.id.foodImage1);
        TextView Menu = (TextView) view.findViewById(R.id.menu1);
       TextView Price = (TextView)view.findViewById(R.id.price1);

        FoodImage.setImageResource(foodImages[i]);
        Menu.setText(MENU[i]);
        Price.setText(price[i]);

        return view;
    }
}
