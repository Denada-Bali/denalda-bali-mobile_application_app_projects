package com.example.fast_service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MyAdapter extends BaseAdapter {

    Context context;
    int[] images;
    String[] motivation;
    LayoutInflater inflater;

public  MyAdapter(Context appiCationContexr, String[] motivation, int[] images){
    this.context = appiCationContexr;
    this.images = images;
    this.motivation = motivation;
    inflater = (LayoutInflater.from(appiCationContexr));

}

    @Override
    public int getCount() {
        return motivation.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.list_item, null);
        TextView Motivation = (TextView) view.findViewById(R.id.motivation);
        ImageView Image = (ImageView)view.findViewById(R.id.image);

        Motivation.setText(motivation[position]);
        Image.setImageResource(images[position]);
        return view;
    }
}
