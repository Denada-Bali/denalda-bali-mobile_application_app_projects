package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static SeekBar seek_Bar;
    private static TextView Show_tv;
    private static ListView ListViewlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekB();
        ListV();

    }

        public void ListV() {
            String[] ToDoList = {"1. Wake up at 8 o'clock and eat breakfast",
                    "2. Lecture at 10:00 to 12:00 PM",
                    "3. Eat lunch and take a short break",
                    "4. Lecture at 14:00 to 17:00 PM",
                    "5. Take the dog for a walk, 18:00 - 19:00",
                    "6. Spend time on social media, 19:00 - 21:00",
                    "7. Eat dinner, 21:00",
                    "8. Study Time"};

//convert in an array using Array adapter

            ListAdapter inAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, ToDoList);

            ListViewlv = (ListView) findViewById(R.id.ListViewlv);

            ListViewlv.setAdapter(inAdapter);

            ListViewlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String ToDoList = String.valueOf(parent.getItemAtPosition(position));

                    Toast.makeText(MainActivity.this, ToDoList, Toast.LENGTH_LONG).show();
                }
            });
        }

     public void seekB(){
         seek_Bar = (SeekBar) findViewById(R.id.seek_Bar);
         Show_tv = (TextView) findViewById(R.id.Show_tv);

         Show_tv.setText("Rating : " + seek_Bar.getProgress() + "/" + seek_Bar.getMax());

         seek_Bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                 switch (progress) {
                     case 1:
                         Toast.makeText(MainActivity.this, "it wasn't a good day! ", Toast.LENGTH_LONG).show();
                         break;
                     case 2:
                         Toast.makeText(MainActivity.this, "it wasn't a good day! ", Toast.LENGTH_LONG).show();
                         break;
                     case 3:
                         Toast.makeText(MainActivity.this, "it wasn't a good day! ", Toast.LENGTH_LONG).show();
                         break;
                     case 4:
                         Toast.makeText(MainActivity.this, "it wasn't a good day! ", Toast.LENGTH_LONG).show();
                         break;
                     case 5:
                         Toast.makeText(MainActivity.this, "it was ok! ", Toast.LENGTH_LONG).show();
                         break;
                     case 6:
                         Toast.makeText(MainActivity.this, "it was ok! ", Toast.LENGTH_LONG).show();
                         break;
                     case 7:
                         Toast.makeText(MainActivity.this, "it was ok! ", Toast.LENGTH_LONG).show();
                         break;
                     case 8:
                         Toast.makeText(MainActivity.this, "it was great! ", Toast.LENGTH_LONG).show();
                         break;
                     case 9:
                         Toast.makeText(MainActivity.this, "it was great! ", Toast.LENGTH_LONG).show();
                         break;
                     case 10:
                         Toast.makeText(MainActivity.this, "it was great! ", Toast.LENGTH_LONG).show();
                         break;
                 }
             }
             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

               }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

                 }
         });

    }
}