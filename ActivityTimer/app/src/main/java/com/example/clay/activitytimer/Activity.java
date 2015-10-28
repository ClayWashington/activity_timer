package com.example.clay.activitytimer;

/**
 * Created by Clay on 10/27/2015.
 */
public class Activity {
    private int id;
    private String activityName;
    private int duration;
    private int time;

    public Activity(String name, int duration){
        activityName = name;
        this.duration = duration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String name) {
        activityName = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String toString() {
        return activityName + ": " + duration;
    }
}
