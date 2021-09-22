package com.example.fast_service;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.AdapterViewFlipper;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class UserDashboard extends AppCompatActivity {
    private AdapterViewFlipper simpleAdapterViewFlipper;
    private ListView simpleList;

    int[] images = {R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4};
    String motivation[] = {"Every day", "is a good day", "for our", "restaurant"};

    String MENU[] = {"Cheese Pizza", "Pizza Bread", "Bruschetta", "Waffles", "Cheesecake", "Panna Cotta", "Cannoli","Jack Daniels","Whiskey" };
    int foodImages[] = {R.drawable.listviw1,R.drawable.listview2,R.drawable.listview3,R.drawable.listview4,R.drawable.listview5,R.drawable.listview6,R.drawable.listview7,R.drawable.listview8,R.drawable.listview9};
    String price[]= {"$ 6.5", "$ 4.5", "$ 9", "$ 11", "$ 14.5", "$ 8", "$ 4.95", "$ 130", "$ 179"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__dashboard);
        
        init();
        MyView();
        Mylist();

    }
    private void init()
    {
        simpleAdapterViewFlipper= (AdapterViewFlipper) findViewById(R.id.simpleAdapterView);
        simpleList = (ListView) findViewById(R.id.simpleList);

    }
    private void MyView(){
        MyAdapter myAdapter = new  MyAdapter (getApplicationContext(),motivation, images);

        simpleAdapterViewFlipper.setAdapter(myAdapter);
        simpleAdapterViewFlipper.setFlipInterval(2000);// change the view in two seconds
        simpleAdapterViewFlipper.setAutoStart(true);
    }
    private void Mylist(){

        MyList myList = new MyList(getApplicationContext(), foodImages, MENU, price );
        simpleList.setAdapter(myList);
    }

    private void loadFragment(Fragment fragment) {

        FragmentManager fm = getFragmentManager();  // create a FragmentManager

        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.frame_Layout, fragment); // replace the FrameLayout with new Fragment

        fragmentTransaction.commit(); // save the changes
    }
}