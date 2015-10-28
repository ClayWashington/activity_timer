package com.example.clay.activitytimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class SaveData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        DataBaseHandler dbh = new DataBaseHandler(this);

        EditText activityView = (EditText) findViewById(R.id.editText);
        EditText timeView = (EditText) findViewById(R.id.editText2);
        Intent saveData = getIntent();
        String activityName = saveData.getStringExtra("activity");
        int time = saveData.getIntExtra("time", 0);
        String time_ = Integer.toString(time);

        Activity activity = new Activity(activityName, time);

        dbh.addActivity(activity);

        activityView.setText(activityName);
        timeView.setText(time_);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save_data, menu);
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
