package com.example.adrianzatreanu.testlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.myListView);

        ArrayList<String> elems = new ArrayList<>();
        for(int i = 0; i < 100000; i++){
            elems.add(i + "shaworma");
        }

        listview.setAdapter(new CustomAdapter(this, elems));

    }
}
