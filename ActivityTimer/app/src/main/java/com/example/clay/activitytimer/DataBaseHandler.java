package com.example.clay.activitytimer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clay on 10/27/2015.
 */
public class DataBaseHandler extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "activity_timerDB.db";
        public static final String TABLE_ACTIVITIES = "activities";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_DAY = "day";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_ACTIVITY = "activity";
        public static final String COLUMN_DURATION = "duration";

        //public com.example.clay.activitytimer.DataBaseHandler(Context context, String name,
        //                       SQLiteDatabase.CursorFactory factory, int version) {
        public DataBaseHandler(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_ACTIVITIES_TABLE = "CREATE TABLE " + TABLE_ACTIVITIES +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_TIME +
                    " TEXT " + COLUMN_DAY + " TEXT " + COLUMN_DATE + " TEXT " +
                    COLUMN_ACTIVITY + " TEXT," + COLUMN_DURATION + " TEXT" + ")";
            db.execSQL(CREATE_ACTIVITIES_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITIES);
        }

        public void addActivity(Activity activity){
            GregorianCalendar calendar = new GregorianCalendar();
            SimpleDateFormat tf = new SimpleDateFormat("h:mm a 's' E 's' MM/d//yyyy");
            String[] currentTime = tf.format(calendar.getTime()).split("s");

            ContentValues values = new ContentValues();
            values.put(COLUMN_TIME, currentTime[0]);
            values.put(COLUMN_DAY, currentTime[1]);
            values.put(COLUMN_DATE, currentTime[2]);
            values.put(COLUMN_ACTIVITY, activity.getActivityName());
            values.put(COLUMN_DURATION, activity.getDuration());
            SQLiteDatabase db = this.getWritableDatabase();
            db.insert(TABLE_ACTIVITIES, null, values);
            db.close();
        }

        public void deleteActivity(int id) {
            SQLiteDatabase db = this.getWritableDatabase();
            String[] whereArg = {Integer.toString(id)};
            db.delete(TABLE_ACTIVITIES, COLUMN_ID + " = ?", whereArg);
            db.close();
        }

        public List<Activity> listActivities() {
            List<Activity> activities = new ArrayList<Activity>();
            String query = "SELECT * FROM " + TABLE_ACTIVITIES;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            String activityName;
            int duration;

            while (cursor.moveToNext()) {
                Activity activity = new Activity(cursor.getString(1),
                        Integer.parseInt(cursor.getString(2)));
                activity.setId(Integer.parseInt(cursor.getString(0)));
                activities.add(activity);
            }
            return activities;
        }
}
