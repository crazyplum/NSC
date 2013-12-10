package com.plum.mthighlight.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Pair;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.plum.mthighlight.R;

import java.util.ArrayList;

public class FirstStageActivity extends ActionBarActivity {

    int testcase;
    int current_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        current_state = 0;
        Intent intent = getIntent();
        testcase = intent.getIntExtra("testcase", -1);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.okay:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        LinearLayout row1;
        LinearLayout row2;
        LinearLayout row3;
        LinearLayout row4;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_first, container, false);

            row1 = (LinearLayout) rootView.findViewById(R.id.row1);
            row2 = (LinearLayout) rootView.findViewById(R.id.row2);
            row3 = (LinearLayout) rootView.findViewById(R.id.row3);
            row4 = (LinearLayout) rootView.findViewById(R.id.row4);

            String sentence = IntroActivity.oriSentences.get(0);
            ArrayList<Pair<String, String>> pairs = IntroActivity.sentencesMap.get(0);
            String words[] = sentence.split(" ");
            int count = 0;

            for(int i = 0; i < words.length; i++){
                if(count + words[i].length() < 33){
                    row1.addView(generateTextView(words[i]));
                    row1.addView(generateTextView(" "));
                    count += words[i].length() + 1;
                }else if(count + words[i].length() < 66){
                    row2.addView(generateTextView(words[i]));
                    row2.addView(generateTextView(" "));
                    count += words[i].length() + 1;
                }else if(count + words[i].length() < 99){
                    row3.addView(generateTextView(words[i]));
                    row3.addView(generateTextView(" "));
                    count += words[i].length() + 1;
                }else{
                    row4.addView(generateTextView(words[i]));
                    row4.addView(generateTextView(" "));
                    count += words[i].length() + 1;
                }
            }

            return rootView;
        }

        private TextView generateTextView(String word){
            TextView tv = new TextView(getActivity());
            tv.setText(word);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            return tv;
        }
    }

}
