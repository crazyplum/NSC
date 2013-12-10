package com.plum.mthighlight.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ggm on 2013/12/11.
 */
public class Tracker {

    private Tracker instatnce;
    private JSONArray array;
    private Activity activity;

    public Tracker(Activity activity) {
        array = new JSONArray();
        this.activity = activity;
    }

    public void start() {

        Intent intent = activity.getIntent();
        JSONObject object = new JSONObject();
        try {
            object.put("type", "start");
            object.put("testcase", intent.getIntExtra("testcase", -1));
            object.put("time", new Date().getTime());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        array.put(object);
    }

    public void addEvent(String typeName, String phrase) {
        JSONObject object = new JSONObject();
        try {
            object.put("type", typeName);
            object.put("phrase", phrase);
            object.put("time", new Date().getTime());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        array.put(object);
    }

    public File end() {
        JSONObject object = new JSONObject();
        try {
            object.put("type", "end");
            object.put("time", new Date().getTime());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        array.put(object);
        return write();
    }

    private File write() {

        //TODO(ggm) should add tester id to be the prefix at fileName.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = "log_"+sdf.format(new Date())+".txt";

        File dir = Environment.getExternalStorageDirectory();
        File file = new File(dir+"/nsc", fileName);

        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(array.toString(4).getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
