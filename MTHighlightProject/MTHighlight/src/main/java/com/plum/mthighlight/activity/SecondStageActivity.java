package com.plum.mthighlight.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plum.mthighlight.R;
import com.plum.mthighlight.utility.LinkTouchMovementMethod;
import com.plum.mthighlight.utility.TouchableSpan;

public class SecondStageActivity extends ActionBarActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        TextView oriTextView;
        TextView tranTextView;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_second, container, false);
            oriTextView = (TextView) rootView.findViewById(R.id.ori_sentence);
            tranTextView = (TextView) rootView.findViewById(R.id.tran_sentence);



            oriTextView.setMovementMethod(new LinkTouchMovementMethod());

            String ts = "Let's have dinner at 6";
            String splits[] = ts.split(" ");
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(ts);
            int offset = 0;
            for(int i = 0 ;i < splits.length; i++){
                spannableStringBuilder.setSpan(new TouchableSpan() {
                    @Override
                    public boolean onTouch(View widget, MotionEvent m) {
                        //widget.setBackgroundColor(getResources().getColor(R.color.yellow));
                        return true;
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        ds.setUnderlineText(false);
                        //ds.setColor(getResources().getColor(R.color.yellow));
                    }
                }, offset, offset+splits[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                offset += splits[i].length()+1;
            }
            oriTextView.setText(spannableStringBuilder, TextView.BufferType.SPANNABLE);


            tranTextView.setMovementMethod(new LinkTouchMovementMethod());

            ts = "六點一起吃晚餐吧";
            String[] newsplits= {"六點", "一起", "吃", "晚餐", "吧"};
            spannableStringBuilder = new SpannableStringBuilder(ts);
            offset = 0;
            for(int i = 0 ;i < newsplits.length; i++){
                spannableStringBuilder.setSpan(new TouchableSpan() {
                    @Override
                    public boolean onTouch(View widget, MotionEvent m) {
                        //widget.setBackgroundColor(getResources().getColor(R.color.yellow));
                        return true;
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        ds.setUnderlineText(false);
                        //ds.setColor(getResources().getColor(R.color.yellow));
                    }
                }, offset, offset+newsplits[i].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                offset += newsplits[i].length();
            }
            tranTextView.setText(spannableStringBuilder, TextView.BufferType.SPANNABLE);
            return rootView;
        }
    }

}
