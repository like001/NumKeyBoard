package com.ro.keyboard.psw_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tolu on 2017/11/29.
 */

public class InputView extends LinearLayout implements PasswordKeyboardView.OnKeyDown{
    private static final String TAG = "InputView" ;
    public final int MAX_COUNT = 4;
    private final int PADDING = 28;
    private int mCurWaitToPutIn;
    private boolean mIsFull = false;
    ArrayList<InputChildView> mChildList = new ArrayList<>(MAX_COUNT);
    public InputView(Context context) {
        super(context);
        init(context);
    }

    public InputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        for(int i=0;i<MAX_COUNT;i++){
            InputChildView inputChildView = new InputChildView(context);
            mChildList.add(inputChildView);
            this.addView(inputChildView);
        }
        if(mChildList.size() > 0){
            mChildList.get(0).changeToWaitState();
        }
        setWillNotDraw(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int countChild = this.getChildCount();
        for(int i=0;i<countChild;i++){
            View child = this.getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);
        }
        setMeasuredDimension(getChildAt(0).getMeasuredWidth()*countChild+PADDING*(countChild-1),getChildAt(0).getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int countChild = this.getChildCount();
        for (int i = 0; i < countChild; i++) {
            View child = this.getChildAt(i);
            child.layout(i * (PADDING + child.getMeasuredWidth()), 0, i * (PADDING + child.getMeasuredWidth()) + child.getMeasuredWidth(),
                    child.getMeasuredHeight());
        }

    }

    @Override
    public void onKeyDown(int type, int value) {
        switch (type){
            case PasswordKeyboardView.KeyBoardEvent.NUMBER:
                putInText(value);
                break;
            case PasswordKeyboardView.KeyBoardEvent.DELETE:
                deleteText();
                break;
            case PasswordKeyboardView.KeyBoardEvent.ENTER:
                onEnterClick();
                break;
            default:
                break;
        }
    }

    private void onEnterClick() {
        if(mIsFull){
            String inputPsw = getInputPsw();
            if(checkEnterPsw(inputPsw)){
                Log.e(TAG,inputPsw);
                return;
            }
        }
        Toast.makeText(getContext(),"密码还没有输入完毕",Toast.LENGTH_SHORT);
    }



    private void putInText(int value ) {
        if(!checkIsSingleNum(value)){
            throw new RuntimeException("input "+value +" is not single number");
        }
        mChildList.get(mCurWaitToPutIn).setText(value+"");
        onNewTextPutIn();
    }

    private void deleteText(){
        mChildList.get(mCurWaitToPutIn).setText("");
        onDeleteText();
    }

    private void onDeleteText() {
        if(mCurWaitToPutIn != 0 && mCurWaitToPutIn != MAX_COUNT -1){
            mCurWaitToPutIn--;
        }
        if(mCurWaitToPutIn == MAX_COUNT -1 && !mIsFull){
            mCurWaitToPutIn--;
        }
        mChildList.get(mCurWaitToPutIn).changeToWaitState();
        mIsFull = false;
    }

    private void onNewTextPutIn() {
        if(mCurWaitToPutIn != MAX_COUNT-1){
            mCurWaitToPutIn++;
            mChildList.get(mCurWaitToPutIn).changeToWaitState();
            return;
        }
        mIsFull =  true;
    }

    public String getInputPsw() {
        StringBuilder sb = new StringBuilder(MAX_COUNT);
        for (InputChildView child:mChildList){
            sb  = sb.append(child.getText());
        }
        return sb.toString();
    }

    private boolean checkIsSingleNum(int value) {
        if(value<0 || value>9){
            return false;
        }
        return true;
    }

    private boolean checkEnterPsw(String inputPsw) {
        String regEx = "^\\d{"+MAX_COUNT+"}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(inputPsw);
        // 字符串是否与正则表达式相匹配
        return  matcher.matches();
    }
}
