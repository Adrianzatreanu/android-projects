package com.example.adrianzatreanu.monthslayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private RecyclerView recyclerView;
    private MonthAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
   //     listView = (ListView) findViewById(R.id.myListView);

        TreeMap<Month, ArrayList<Run>> map = new TreeMap<>();

        ArrayList<Run> novemberRuns = new ArrayList<>();
        ArrayList<Run> decemberRuns = new ArrayList<>();
        ArrayList<Run> octoberRuns = new ArrayList<>();

        String[] icons;
        icons = new String[]{null, "sad", "track", "sun"};
        novemberRuns.add(new Run(11, "23", "2015", "6'56", "43.57", "6.34", icons));

        icons = new String[]{null, "happy", "track", "sun"};
        novemberRuns.add(new Run(11, "16", "2015", "6'12", "36.36", "5.90", icons));

        icons = new String[]{"number1", "happy", "track", "sun"};
        novemberRuns.add(new Run(11, "19", "2015", "6'34", "1:17", "11.81", icons));

        icons = new String[]{"number3", "sad", "highway", "sun"};
        decemberRuns.add(new Run(12, "14", "2016", "8'58", "1:09", "7.72", icons));

        icons = new String[]{"number", "sad", "track", "rain"};
        decemberRuns.add(new Run(12, "23", "2016", "9'22", "1:12", "7.58", icons));

        icons = new String[]{null, "happy", "track", "sun"};
        octoberRuns.add(new Run(10, "21", "2016", "6'23", "53.46", "7.42", icons));

        icons = new String[]{null, "sad", "track", "rain"};
        octoberRuns.add(new Run(10, "18", "2016", "6'35", "51.48", "7.51", icons));

        Month december = new Month(12, "7.72", "8'58", "1846");
        Month november = new Month(11, "44.86", "6'53", "11061");
        Month october = new Month(10, "15.32", "6'40", "4232");

        recyclerView = (RecyclerView) findViewById(R.id.myListView);

        ArrayList<Run> runsList = new ArrayList<>();
        runsList.addAll(novemberRuns);
        runsList.addAll(decemberRuns);
        runsList.addAll(octoberRuns);
        mAdapter = new MonthAdapter(runsList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        map.put(december, decemberRuns);
        map.put(november, novemberRuns);
        map.put(october, octoberRuns);


    //    listView.setAdapter(new CustomAdapter(this, map));

    }

}
