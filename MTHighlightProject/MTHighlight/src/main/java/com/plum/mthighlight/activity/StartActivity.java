package com.plum.mthighlight.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.plum.mthighlight.R;

/**
 * Created by plum on 12/9/13.
 */
public class StartActivity extends Activity{

    LinearLayout mButtonlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mButtonlist = (LinearLayout) findViewById(R.id.buttonlist);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mButtonlist.addView(createButton(0, this), params);

    }

    private Button createButton(final int i, final Context cxt){
        Button btn = new Button(this);
        btn.setText(i+1+"");
        btn.setId(i);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(cxt, IntroActivity.class);
                intent.putExtra("testcase", i);
                cxt.startActivity(intent);
            }
        });
        return btn;
    }


}
