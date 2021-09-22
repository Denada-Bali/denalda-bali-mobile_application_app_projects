package com.example.fast_service;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.AdapterViewFlipper;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


public class WaiterDashboard<val> extends AppCompatActivity {

    private AdapterViewFlipper simpleAdapterViewFlipper1;
    private ListView simpleList;

    int[] images = {R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4};
    String motivation[] = {"Every day", "is a good day", "for your", "restaurant"};

    ArrayAdapter<String> adapter;
    String MENU[] = {"Cheese Pizza", "Pizza Bread", "Bruschetta", "Waffles", "Cheesecake", "Panna Cotta", "Cannoli","Jack Daniels","Whiskey" };
    int foodImages[] = {R.drawable.listviw1,R.drawable.listview2,R.drawable.listview3,R.drawable.listview4,R.drawable.listview5,R.drawable.listview6,R.drawable.listview7,R.drawable.listview8,R.drawable.listview9};
    String price[]= {"$ 6.5", "$ 4.5", "$ 9", "$ 11", "$ 14.5", "$ 8", "$ 4.95", "$ 130", "$ 179"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_dashboard);

        init();
        MyView();
        Mylist();
    }

    private void init()
    {
        simpleAdapterViewFlipper1= (AdapterViewFlipper) findViewById(R.id.simpleAdapterViewF);
        simpleList = (ListView) findViewById(R.id.simpleL);

    }

    private void MyView(){
        MyAdapter myAdapter = new  MyAdapter (getApplicationContext(),motivation, images);

        simpleAdapterViewFlipper1.setAdapter(myAdapter);
        simpleAdapterViewFlipper1.setFlipInterval(2000);// change the view in two seconds
        simpleAdapterViewFlipper1.setAutoStart(true);
    }
    private void Mylist(){

         MyList myList = new MyList(getApplicationContext(), foodImages, MENU, price);
        simpleList.setAdapter(myList);

       /* adapter= new ArrayAdapter< String >(this,
                android.R.layout.simple_list_item_multiple_choice, MENU);
        simpleList.setAdapter(adapter);*/
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        //return super.onCreateOptionsMenu(menu);
    return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int id = item.getItemId();
       if(id == R.id.item_done){
           String itemSelected = "Selected items: \n";
           for(int i = 0; i < simpleList.getCount(); i++){
               if(simpleList.isItemChecked(i)){
                   itemSelected += simpleList.getItemAtPosition(i)+"\n";
               }
           }
           Toast.makeText(this, itemSelected, Toast.LENGTH_SHORT).show();
       }
        return super.onOptionsItemSelected(item);
    }*/

    private void loadFragment(Fragment fragment) {

        FragmentManager fm = getFragmentManager();  // create a FragmentManager

        // create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.frame_Layout1, fragment); // replace the FrameLayout with new Fragment

        fragmentTransaction.commit(); // save the changes
    }
}