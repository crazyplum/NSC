package com.plum.mthighlight.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
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
import com.plum.mthighlight.utility.LinkTouchMovementMethod;
import com.plum.mthighlight.utility.TestConditionOrder;
import com.plum.mthighlight.utility.TouchableSpan;

import java.util.ArrayList;

public class ThirdStageActivity extends ActionBarActivity {

    PlaceholderFragment mFragment;
    int testcase;
    int current_state;


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

        LinearLayout orow1;
        LinearLayout orow2;
        LinearLayout orow3;
        LinearLayout orow4;
        LinearLayout orow5;
        LinearLayout trow1;
        LinearLayout trow2;
        LinearLayout trow3;

        ArrayList<TextView> oriTextviewList;
        ArrayList<TextView> tranTextviewList;
        int index;
        int testcase;
        int current_state;

        public PlaceholderFragment(int t, int c) {
            testcase = t;
            current_state = c;
            index = 0;
            Log.d("activity third", Integer.toString(t) + " " + Integer.toString(c));
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_third, container, false);

            orow1 = (LinearLayout) rootView.findViewById(R.id.orow1);
            orow2 = (LinearLayout) rootView.findViewById(R.id.orow2);
            orow3 = (LinearLayout) rootView.findViewById(R.id.orow3);
            orow4 = (LinearLayout) rootView.findViewById(R.id.orow4);
            orow5 = (LinearLayout) rootView.findViewById(R.id.orow5);
            trow1 = (LinearLayout) rootView.findViewById(R.id.trow1);
            trow2 = (LinearLayout) rootView.findViewById(R.id.trow2);
            trow3 = (LinearLayout) rootView.findViewById(R.id.trow3);

            generateOriSentence(TestConditionOrder.ORDER.get(testcase)[index + 3*current_state]);
            generateTranSentence(TestConditionOrder.ORDER.get(testcase)[index + 3*current_state]);


            return rootView;
        }

        private TextView generateOriTextView(String word){
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
                    for (TextView tv : oriTextviewList) {
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
                    for (TextView tv : tranTextviewList) {
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

        private TextView generateTranTextView(String word, String group){
            TextView tv = new TextView(getActivity());
            tv.setText(word);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
            tv.setTag(R.id.highlight, Boolean.FALSE);
            tv.setTag(R.id.group, group);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tag = (String) view.getTag(R.id.group);
                    Boolean highlight = (Boolean) view.getTag(R.id.highlight);
                    for (TextView tv : tranTextviewList) {
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
                    for (TextView tv : oriTextviewList) {
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

        private void generateOriSentence(int k){

            String sentence = IntroActivity.oriSentences.get(k);
            ArrayList<Pair<String, String>> pairs = IntroActivity.sentencesMap.get(k);
            oriTextviewList = new ArrayList<TextView>();

            String words[] = sentence.split(" ");
            int count = 0;
            TextView tv, sv;

            for(int i = 0; i < words.length; i++){
                tv = generateOriTextView(words[i]);
                sv = generateOriTextView(" ");

                if(count + words[i].length() <= 32){
                    orow1.addView(tv);
                    orow1.addView(sv);
                    count += words[i].length() + 1;
                }else if(count + words[i].length() <= 59){
                    orow2.addView(tv);
                    orow2.addView(sv);
                    count += words[i].length() + 1;
                }else if(count + words[i].length() <= 89){
                    orow3.addView(tv);
                    orow3.addView(sv);
                    count += words[i].length() + 1;
                }else if(count + words[i].length() <= 119){
                    orow4.addView(tv);
                    orow4.addView(sv);
                    count += words[i].length() + 1;
                }else if(count + words[i].length() <= 150){
                    orow5.addView(tv);
                    orow5.addView(sv);
                    count += words[i].length() + 1;
                }
                oriTextviewList.add(tv);
            }

            for(int i = 0; i < pairs.size(); i++){
                Pair<String, String> p = pairs.get(i);
                String str = p.first;
                String w[]  = str.split(" ");
                Log.d("first w", str);
                for(int j = 0; j < w.length; j++){
                    for(TextView t: oriTextviewList){
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

        private void generateTranSentence(int k){

            String sentence = IntroActivity.tranSentences.get(k);
            ArrayList<Pair<String, String>> pairs = IntroActivity.sentencesMap.get(k);
            tranTextviewList = new ArrayList<TextView>();;
            int count = 0;
            for(int i = 0; i < pairs.size(); i++){
                String str = pairs.get(i).second;
                for(int j = 0; j < str.length(); j++){
                    char c = str.charAt(j);
                    TextView tv = generateTranTextView(Character.toString(c), Integer.toString(i+1));
                    if(c != '\uff0c') tranTextviewList.add(tv);
                    if(count + 1 <= 17){
                        trow1.addView(tv);
                        count++;
                    }else if(count +1 <= 34 ){
                        trow2.addView(tv);
                        count++;
                    }else if(count +1 <= 51){
                        trow3.addView(tv);
                        count++;
                    }
                }
            }

        }

        public void getNewSentence(){
            index++;
            orow1.removeAllViews();
            orow2.removeAllViews();
            orow3.removeAllViews();
            orow4.removeAllViews();
            orow5.removeAllViews();
            trow1.removeAllViews();
            trow2.removeAllViews();
            trow3.removeAllViews();

            generateOriSentence(TestConditionOrder.ORDER.get(testcase)[index + 3*current_state]);
            generateTranSentence(TestConditionOrder.ORDER.get(testcase)[index + 3*current_state]);
        }
    }

}
