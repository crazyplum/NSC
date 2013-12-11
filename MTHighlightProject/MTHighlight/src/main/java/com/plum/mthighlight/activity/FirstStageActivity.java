package com.plum.mthighlight.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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
import com.plum.mthighlight.utility.TestConditionOrder;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FirstStageActivity extends ActionBarActivity {

    int testcase;
    int current_state;
    PlaceholderFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        testcase = intent.getIntExtra("testcase", -1);
        current_state = intent.getIntExtra("current_state", -1);
        mFragment = new PlaceholderFragment(testcase, current_state);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, mFragment)
                    .commit();
        }


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
                if(mFragment.index < 2){
                    mFragment.getNewSentence();
                }else{
                    if(current_state < 2){
                        Intent intent = new Intent();
                        intent.setClass(this, TestConditionOrder.TESTCASE.get(testcase % 6).get(current_state+1));
                        intent.putExtra("testcase", testcase);
                        intent.putExtra("current_state", current_state + 1);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent();
                        intent.setClass(this, EndActivity.class);
                        startActivity(intent);
                    }
                }
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
        LinearLayout row5;
        LinearLayout row6;

        ArrayList<TextView> textviewList;
        int index;
        int testcase;
        int current_state;

        public PlaceholderFragment(int t, int c) {
            testcase = t;
            current_state = c;
            index = 0;
            Log.d("activity first", Integer.toString(t) + " " + Integer.toString(c));
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_first, container, false);

            row1 = (LinearLayout) rootView.findViewById(R.id.row1);
            row2 = (LinearLayout) rootView.findViewById(R.id.row2);
            row3 = (LinearLayout) rootView.findViewById(R.id.row3);
            row4 = (LinearLayout) rootView.findViewById(R.id.row4);
            row5 = (LinearLayout) rootView.findViewById(R.id.row5);
            row6 = (LinearLayout) rootView.findViewById(R.id.row6);

            generateSentence(TestConditionOrder.ORDER.get(testcase)[index + current_state*3]);

            return rootView;
        }

        private TextView generateTextView(String word){
            TextView tv = new TextView(getActivity());
            tv.setText(word);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            tv.setTag(R.id.highlight, Boolean.FALSE);
            tv.setTag(R.id.group, "0");
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

            String words[] = sentence.split(" ");
            int count = 0;
            TextView tv, sv;

            for(int i = 0; i < words.length; i++){
                tv = generateTextView(words[i]);
                sv = generateTextView(" ");

                if(count + words[i].length() <= 32){
                    row1.addView(tv);
                    row1.addView(sv);
                    count += words[i].length() + 1;
                }else if(count + words[i].length() <= 59){
                    row2.addView(tv);
                    row2.addView(sv);
                    count += words[i].length() + 1;
                }else if(count + words[i].length() <= 89){
                    row3.addView(tv);
                    row3.addView(sv);
                    count += words[i].length() + 1;
                }else if(count + words[i].length() <= 119){
                    row4.addView(tv);
                    row4.addView(sv);
                    count += words[i].length() + 1;
                }else if(count + words[i].length() <= 150){
                    row5.addView(tv);
                    row5.addView(sv);
                    count += words[i].length() + 1;
                }else{
                    row6.addView(tv);
                    row6.addView(sv);
                }
                textviewList.add(tv);
            }

            for(int i = 0; i < pairs.size(); i++){
                Pair<String, String> p = pairs.get(i);
                String str = p.first;
                String w[]  = str.split(" ");
                Log.d("first w", str);
                for(int j = 0; j < w.length; j++){
                    for(TextView t: textviewList){
                        String word = (String)t.getText();
                        word = word.replaceAll("[^a-zA-z0-9\']", "");
                        if(word.equals(w[j])){
                            if((String)t.getTag(R.id.group)=="0"){
                                t.setTag(R.id.group, Integer.toString(i+1));
                                break;
                            }
                        }
                    }
                }
            }
        }

        public void getNewSentence(){
            index++;
            row1.removeAllViews();
            row2.removeAllViews();
            row3.removeAllViews();
            row4.removeAllViews();
            row5.removeAllViews();
            row6.removeAllViews();
            generateSentence(TestConditionOrder.ORDER.get(testcase)[index + current_state*3]);
        }
    }

}
