package com.plum.mthighlight.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.plum.mthighlight.R;
import com.plum.mthighlight.utility.TestConditionOrder;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by plum on 12/9/13.
 */
public class IntroActivity extends Activity{

    public static List<String> oriSentences;
    public static List<String> tranSentences;
    public static List<ArrayList<Pair<String, String>>> sentencesMap;

    private int testcase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Intent intent = getIntent();
        testcase = intent.getIntExtra("testcase", -1);
        if(testcase == -1){
            Log.d("intro activity", "testcase wrong");
        }else{

            readData();

            Button btn = (Button) findViewById(R.id.iknow);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(IntroActivity.this, TestConditionOrder.TESTCASE.get(testcase % 6).get(0));
                    intent.putExtra("testcase", testcase);
                    intent.putExtra("current_state", 0);
                    IntroActivity.this.startActivity(intent);
                }
            });
        }
    }



    private void readData(){

        oriSentences = new ArrayList <String>();
        tranSentences = new ArrayList<String>();
        sentencesMap = new ArrayList<ArrayList<Pair<String, String>>>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.sentence_result)));
        try{
            String line = reader.readLine();
            int i = 0;
            while(line != null){
                JSONArray jsonArray = new JSONArray(line);
                oriSentences.add(i, jsonArray.getString(0));
                tranSentences.add(i, jsonArray.getString(1));
                JSONArray wordpair = jsonArray.getJSONArray(2);
                ArrayList<Pair<String, String>> pairList = new ArrayList<Pair<String, String>>();
                for(int j = 0; j < wordpair.length(); j++){
                    JSONArray tmp = wordpair.getJSONArray(j);
                    Pair pair = new Pair<String, String>(tmp.getString(0), tmp.getString(1));
                    pairList.add(pair);
                }
                sentencesMap.add(i, pairList);
                line = reader.readLine();
                i++;
            }

            for(i = 0; i < oriSentences.size(); i++){
                Log.d("intro activity", oriSentences.get(i) + "\n" + tranSentences.get(i));
            }


        }catch(IOException e){
            Log.d("intro activity", "read data error");
        }catch(JSONException e){
            Log.d("intro activity", "json exception");
        }
    }
}
