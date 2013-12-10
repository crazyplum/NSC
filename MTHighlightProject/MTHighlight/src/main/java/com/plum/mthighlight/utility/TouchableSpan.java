package com.plum.mthighlight.utility;

import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.plum.mthighlight.R;


public abstract class TouchableSpan extends CharacterStyle implements UpdateAppearance{

    public abstract boolean onTouch(View widget, MotionEvent m);

    @Override
    public abstract void updateDrawState(TextPaint ds);

    public void changeBackgroundColor(View widget, TextPaint ds){
        Log.d("change color", "here");
        ds.setColor(widget.getResources().getColor(R.color.yellow));
    }

}
