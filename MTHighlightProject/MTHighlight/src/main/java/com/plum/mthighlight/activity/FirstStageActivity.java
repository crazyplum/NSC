package com.plum.mthighlight.activity;

import android.content.Intent;
import android.graphics.Color;
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

import org.w3c.dom.Text;

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

        Intent intent = getIntent();
        testcase = intent.getIntExtra("testcase", -1);
        current_state = intent.getIntExtra("current_state", -1);
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
        ArrayList<TextView> textviewList;
        int index = 0;

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

            generateSentence();

            return rootView;
        }

        private TextView generateTextView(String word, String group){
            TextView tv = new TextView(getActivity());
            tv.setText(word);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            tv.setTag(R.id.group, group);
            tv.setTag(R.id.highlight, Boolean.FALSE);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tag = (String) view.getTag(R.id.group);
                    Boolean highlight = (Boolean) view.getTag(R.id.highlight);
                    for (TextView tv : textviewList) {
                        if ((String) tv.getTag(R.id.group) == tag) {
                            if (!highlight) {
                                tv.setBackgroundColor(getResources().getColor(R.color.yellow));
                                tv.setTag(R.id.highlight, Boolean.TRUE);
                            } else {
                                tv.setBackgroundColor(Color.TRANSPARENT);
                                tv.setTag(R.id.highlight, Boolean.FALSE);
                            }
                        }
                    }
                }
            });
            return tv;
        }

        private void generateSentence(int k){
            String sentence = IntroActivity.oriSentences.get(k);
            ArrayList<Pair<String, String>> pairs = IntroActivity.sentencesMap.get(k);
            textviewList = new ArrayList<TextView>();

            int count = 0;
            for(int j = 0; j < pairs.size(); j++){
                Pair<String, String> p = pairs.get(j);
                TextView tv, spacetv;
                String str = p.first;
                String words[] = str.split(" ");

                for(int i = 0; i < words.length; i++){
                    tv = generateTextView(words[i], Integer.toString(j));
                    spacetv = generateTextView(" ", Integer.toString(j));
                    if(count + words[i].length() < 33){
                        row1.addView(tv);
                        row1.addView(spacetv);
                        count += words[i].length() + 1;
                    }else if(count + words[i].length() < 66){
                        row2.addView(tv);
                        row2.addView(spacetv);
                        count += words[i].length() + 1;
                    }else if(count + words[i].length() < 99){
                        row3.addView(tv);
                        row3.addView(spacetv);
                        count += words[i].length() + 1;
                    }else{
                        row4.addView(tv);
                        row4.addView(spacetv);
                        count += words[i].length() + 1;
                    }
                    textviewList.add(tv);
                    textviewList.add(spacetv);
                }
            }
        }
    }

}
