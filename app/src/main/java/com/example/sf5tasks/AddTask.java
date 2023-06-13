package com.example.sf5tasks;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;

public class AddTask extends AsyncTask<String, Void, Void> {
    Connection connection;
    String TAG = "AddTask";

    public AddTask(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected Void doInBackground(String... data) {
        String text = data[0];
        String type = data[1];
        String goal = data[2];
        String xp = data[3];
        String points = data[4];
        String time = data[5];
        String query = "INSERT INTO tasks (text, type, goal, xp, points, time) values ('"
                + text + "', '" + type + "', '" + goal + "', '" + xp + "', '" + points
                + "', '" + time + "')";
        try {
            connection.createStatement().executeUpdate(query);
            Log.i(TAG, "Task succeed");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Task failed");
        }
        return null;
    }
}
