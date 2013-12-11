package com.plum.mthighlight.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
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

public class SecondStageActivity extends ActionBarActivity {

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

        mFragment = new PlaceholderFragment(testcase);
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
                mFragment.getNewSentence();
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
        int index;
        int testcase;

        public PlaceholderFragment(int t) {
            testcase = t;
            index = 0;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_first, container, false);

            row1 = (LinearLayout) rootView.findViewById(R.id.row1);
            row2 = (LinearLayout) rootView.findViewById(R.id.row2);
            row3 = (LinearLayout) rootView.findViewById(R.id.row3);
            row4 = (LinearLayout) rootView.findViewById(R.id.row4);

            generateSentence(TestConditionOrder.ORDER.get(testcase)[index]);

            return rootView;
        }

        private TextView generateTextView(String word, String group){
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

            String sentence = IntroActivity.tranSentences.get(k);
            ArrayList<Pair<String, String>> pairs = IntroActivity.sentencesMap.get(k);
            textviewList = new ArrayList<TextView>();
            int count = 0;
            for(int i = 0; i < pairs.size(); i++){
                String str = pairs.get(i).second;
                for(int j = 0; j < str.length(); j++){
                    char c = str.charAt(j);
                    TextView tv = generateTextView(Character.toString(c), Integer.toString(i+1));
                    if(c != '\uff0c') textviewList.add(tv);
                    if(count + 1 <= 17){
                        row1.addView(tv);
                        count++;
                    }else if(count +1 <= 34 ){
                        row2.addView(tv);
                        count++;
                    }else if(count +1 <= 51){
                        row3.addView(tv);
                        count++;
                    }else{
                        row4.addView(tv);
                        count++;
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
            generateSentence(TestConditionOrder.ORDER.get(testcase)[index]);
        }
    }

}
