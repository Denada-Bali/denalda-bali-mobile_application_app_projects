package com.example.fast_service;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class LocationFragment extends Fragment {
    View view;

    private TextView address, phoneNumber_, rate_text, website;
    private RatingBar RatingStars;
    private Intent googleMap;
    private Intent chooser;
    float myRating = 0 ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.location_fragment, container, false);

        init();
        initlistners();
        return view;
    }


    private void init() {
        RatingStars = (RatingBar) view.findViewById(R.id.rate);
        address =(TextView) view.findViewById(R.id.info5);
        phoneNumber_ =(TextView) view.findViewById(R.id.info4);
        rate_text =(TextView) view.findViewById(R.id.rate_text);
        website =(TextView) view.findViewById(R.id.website);

   }

   private void initlistners() {
       RatingStars.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               RatingStars();
           }
       });
       address.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               GoogleLocation();
           }
       });
       phoneNumber_.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Call();
              // Toast.makeText(getActivity(), "Checked ", Toast.LENGTH_LONG).show();
           }
       });
       website.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               OpenWeb();
               // Toast.makeText(getActivity(), "Checked ", Toast.LENGTH_LONG).show();
           }
       });
    }

    private void OpenWeb() {

        String OpenWeb = "https://www.elevenmadisonpark.com/";
        Uri web = Uri.parse(OpenWeb);
        Intent startIntent = new Intent(Intent.ACTION_VIEW, web);
        if (startIntent.resolveActivity(getActivity().getPackageManager()) !=null );
        startActivity(startIntent);
    }

    public void RatingStars(){
     
        RatingStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int rating = (int) v;
                String message = null;

                myRating = ratingBar.getRating();

                switch (rating){
                    case 1:
                        message = "Sorry to hear that! :(";
                        break;
                    case 2:
                        message = "We always accept suggestions! ";
                        break;
                    case 3:
                        message = "Good enough! ";
                        break;
                    case 4:
                        message = "Great! Thank you! ";
                        break;
                    case 5:
                        message = "Awesome! You are the best! ";
                        break;
                }
                if(message != null){
                rate_text.setText(message);
                }
               // Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void Call(){
        String PhoneNumber = "+1 312-440-1500";

        Uri Phone = Uri.parse("tel:" + PhoneNumber);
        Intent Dial = new Intent(Intent.ACTION_DIAL);
        Dial.setData(Phone);
        startActivity(Dial);

    }

    private void GoogleLocation(){
        googleMap=new Intent(Intent.ACTION_VIEW);
        googleMap.setData(Uri.parse("geo:0,0?q=le  New York,  Madison Ave"));
        startActivity(googleMap);
    }
}