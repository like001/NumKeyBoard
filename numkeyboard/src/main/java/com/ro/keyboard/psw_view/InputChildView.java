package com.ro.keyboard.psw_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by tolu on 2017/11/29.
 */

public class InputChildView extends TextView {

    final  static int SET_WIDTH = 70;
    final  static int SET_HEIGHT = 70;
    final  static int TEXT_SIZE = 30;
    public InputChildView(Context context) {
        super(context);
        init();
    }

    public InputChildView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.setTextSize(TEXT_SIZE);
        this.setTextColor(Color.BLUE);
        this.setGravity(Gravity.CENTER);
        this.setBackgroundColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("tag","onDraw");
        Log.e("tag","width==="+getMeasuredWidth());
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("tag","onMeasure");
        setMeasuredDimension(SET_WIDTH,SET_HEIGHT);
    }

    public void changeToWaitState() {
        this.setText("|");
    }
}
