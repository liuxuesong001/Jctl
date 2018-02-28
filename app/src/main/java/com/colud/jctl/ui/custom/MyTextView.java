package com.colud.jctl.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Jcty on 2017/5/5.
 */

public class MyTextView extends TextView {

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
