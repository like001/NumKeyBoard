package com.ro.keyboard.psw_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.ro.keyboard.R;


/**
 * Created by tolu on 2017/11/29.
 */

public class PasswordKeyboardView extends FrameLayout {

    public PasswordKeyboardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context ctx) {
        View v = View.inflate(ctx, R.layout.view_psw_keyboard,this);
        Button btn1 = (Button) v.findViewById(R.id.btn_text_1);
        Button btn2 = (Button) v.findViewById(R.id.btn_text_2);
        Button btn3 = (Button) v.findViewById(R.id.btn_text_3);
        Button btn4 = (Button) v.findViewById(R.id.btn_text_4);
        Button btn5 = (Button) v.findViewById(R.id.btn_text_5);
        Button btn6 = (Button) v.findViewById(R.id.btn_text_6);
        Button btn7 = (Button) v.findViewById(R.id.btn_text_7);
        Button btn8 = (Button) v.findViewById(R.id.btn_text_8);
        Button btn9 = (Button) v.findViewById(R.id.btn_text_9);
        Button btn0 = (Button) v.findViewById(R.id.btn_text_0);
        Button btn_delete = (Button) v.findViewById(R.id.btn_text_delete);
        Button btn_enter =(Button) v.findViewById(R.id.btn_text_enter);
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                int event = KeyBoardEvent.NUMBER;
                int value = 0;
                if(v.getId() == R.id.btn_text_enter){
                    event = KeyBoardEvent.ENTER;
                }else if(v.getId() == R.id.btn_text_delete){
                    event = KeyBoardEvent.DELETE;
                }else {
                    if(v.getId() == R.id.btn_text_1){
                        value = 1;
                    }else if(v.getId() == R.id.btn_text_2){
                        value = 2;
                    }else if(v.getId() == R.id.btn_text_3){
                        value = 3;
                    }else if(v.getId() == R.id.btn_text_4){
                        value = 4;
                    }else if(v.getId() == R.id.btn_text_5){
                        value = 5;
                    }else if(v.getId() == R.id.btn_text_6){
                        value = 6;
                    }else if(v.getId() == R.id.btn_text_7){
                        value = 7;
                    }else if(v.getId() == R.id.btn_text_8){
                        value = 8;
                    }else if(v.getId() == R.id.btn_text_9){
                        value = 9;
                    }else if(v.getId() == R.id.btn_text_0){
                        value = 0;
                    }
                }
                if(mListener != null){
                    mListener.onKeyDown(event,value);
                }
            }
        };
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btn5.setOnClickListener(listener);
        btn6.setOnClickListener(listener);
        btn7.setOnClickListener(listener);
        btn8.setOnClickListener(listener);
        btn9.setOnClickListener(listener);
        btn0.setOnClickListener(listener);
        btn_delete.setOnClickListener(listener);
        btn_enter.setOnClickListener(listener);
    }

    OnKeyDown mListener;
    public void setKeyboardListener(OnKeyDown listener ){
        mListener = listener;
    }

    interface OnKeyDown{
        /**注册该接口，接收键盘点击事件
         * @param type
         * @param value
         */
        void onKeyDown(int type, int value);
    }

    interface KeyBoardEvent{
        int NUMBER = 0;
        int DELETE = 1;
        int ENTER = 2;
    }
}
