package com.eno.task1.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.widget.AppCompatEditText;

/*
 * CustomEditText to acquire onBackPressed callback
 * @author Sandeep Emekar
 * @since 07/02/2020
 *
 * */

public class CustomEditText extends AppCompatEditText {
    OnBackPressedListener onBackPressedListener;
    Context context;

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
            if (onBackPressedListener != null) {
                onBackPressedListener.onBackPressed();
                return true;
            }
        }
        return false;
    }


    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    public interface OnBackPressedListener {
        void onBackPressed();
    }
}
