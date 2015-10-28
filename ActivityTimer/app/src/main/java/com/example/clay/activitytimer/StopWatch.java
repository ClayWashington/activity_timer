package com.example.clay.activitytimer;

import android.os.SystemClock;
import android.widget.Chronometer;

/**
 * Created by Clay on 10/27/2015.
 */
public class StopWatch {
    private Chronometer chronometer;
    private boolean isRunning;
    private String chronometerDisplay;
    private int recordedTime;  //In milliseconds

    public StopWatch(Chronometer chronometer) {
        this(chronometer, 0);
    }

    public StopWatch(Chronometer chronometer, int recordedTime) {
        this.chronometer = chronometer;
        this.recordedTime = recordedTime;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void start() {
        chronometer.setBase(SystemClock.elapsedRealtime() - recordedTime);
        chronometer.start();
        isRunning = true;
    }

    public void stop() {
        chronometer.stop();
        isRunning = false;
        recordedTime = convertDisplay();
    }

    public int getRecordedTime() {
        return recordedTime;
    }

    public int calculateSecondsElapsed() {
        return recordedTime / 1000;
    }

    public int calculateMinutesElapsed() {
        return (recordedTime / 1000) / 60;
    }

    private void setChronometerDisplay() {
        chronometerDisplay = chronometer.getText().toString();
    }

    //Returns milliseconds
    private int convertDisplay() {
        this.setChronometerDisplay();
        String[] timeArray = this.chronometerDisplay.split(":");
        int numSeconds = Integer.parseInt(timeArray[1]) * 1000;
        int numMinutes = Integer.parseInt(timeArray[0]) * 60 * 1000;
        int numHours = 0;
        if (timeArray.length == 3) {
            numHours = Integer.parseInt(timeArray[2]) * 60 * 60 * 1000;
        }
        return numSeconds + numMinutes + numHours;
    }
}
