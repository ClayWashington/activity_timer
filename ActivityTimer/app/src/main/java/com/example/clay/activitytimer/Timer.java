package com.example.clay.activitytimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;

public class Timer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        final Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
        final EditText activityInput = (EditText) findViewById(R.id.user_activity);
        final Button startButton = (Button) findViewById(R.id.start_button);
        final Button saveButton = (Button) findViewById(R.id.save_button);
        final Button homeButton = (Button) findViewById(R.id.home_button);

        final StopWatch sw = new StopWatch(chronometer);

        final Intent home = new Intent(this, Display.class);
        final Intent saveData = new Intent(this, SaveData.class);
        final Bundle data = new Bundle();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!sw.isRunning()) {
                    sw.start();

                    startButton.setText("stop");
                } else {
                    sw.stop();
                    startButton.setText("start");
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sw.isRunning()) sw.stop();
                String activity = activityInput.getText().toString();
                saveData.putExtra("activity", activity);
                saveData.putExtra("time", sw.calculateSecondsElapsed());
                startActivity(saveData);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(home);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timer, menu);
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
