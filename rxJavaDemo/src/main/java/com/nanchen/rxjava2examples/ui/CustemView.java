package com.nanchen.rxjava2examples.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by liuhe on 13/03/2018.
 */

public class CustemView extends View {
    public CustemView(Context context) {
        super(context);
    }

    public CustemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
