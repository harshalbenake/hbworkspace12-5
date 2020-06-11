package com.elitetechnologies.timelineview;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.elitetechnologies.timelineview.TimelineView.TimelineRow;
import com.elitetechnologies.timelineview.TimelineView.TimelineViewAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    //Create Timeline Rows List
    private ArrayList<TimelineRow> timelineRowsList = new ArrayList<>();
    ArrayAdapter<TimelineRow> myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);// Create Timeline rows List
        // Add Random Rows to the List
        for (int i = 0; i < 15; i++) {
            //add the new row to the list
            timelineRowsList.add(createRandomTimelineRow(i));
        }
        //Create the Timeline Adapter
        myAdapter = new TimelineViewAdapter(this, 0, timelineRowsList,
                //if true, list will be sorted by date
                true);
        //Get the ListView and Bind it with the Timeline Adapter
        ListView timeline_listView = (ListView) findViewById(R.id.timeline_listView);
        timeline_listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        //if you wish to handle the clicks on the rows
        AdapterView.OnItemClickListener adapterListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TimelineRow row = timelineRowsList.get(position);
                Toast.makeText(MainActivity.this, row.getTitle()+"at position"+position, Toast.LENGTH_SHORT).show();
            }
        };
        timeline_listView.setOnItemClickListener(adapterListener);
    }

    //Method to create new Timeline Row
    private TimelineRow createRandomTimelineRow(int id) {
        // Create new timeline row (pass your Id)
        TimelineRow myRow = new TimelineRow(id);

        //to set the row Date (optional)
        myRow.setDate(getRandomDate());
        //to set the row Title (optional)
        myRow.setTitle("Title " + id);
        myRow.setTitleColor(Color.parseColor("#3295a8"));
        //to set the row Description (optional)
        myRow.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        myRow.setDescriptionColor(Color.parseColor("#32a8a4"));
        //to set row Below Line Color (optional)
        myRow.setBellowLineColor(Color.LTGRAY);
        return myRow;
    }

    @SuppressLint("NewApi")
    public Date getRandomDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        Date endDate = new Date();
        try {
            startDate = sdf.parse("14/02/2020");
            long random = ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime());
            endDate = new Date(random);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }

}