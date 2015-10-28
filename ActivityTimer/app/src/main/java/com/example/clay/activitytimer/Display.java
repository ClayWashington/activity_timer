package com.example.clay.activitytimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.ArrayList;

public class Display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        DataBaseHandler dbh = new DataBaseHandler(this);
        List<Activity> activitiesList = dbh.listActivities();
        String[] activities = new String[activitiesList.size()];
        for (int i = 0; i < activities.length; i++) {
            activities[i] = activitiesList.get(i).toString();
        }

        ArrayAdapter activitiesAdapter = new ArrayAdapter<String>(
                this,
                R.layout.activity_display,
                //R.id.listActivities,
                activities
        );
        for (int i = 0; i < 50; i++) {
            System.out.println("There are currently: " + activities.length);
        }
        //ListView listView = (ListView) findViewById(R.id.listActivities);
        //listView.setAdapter(activitiesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
