package com.eno.task1;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/*
 *
 * It contains various utility methods
 * @author Sandeep Emekar
 * @since 06/02/2020
 *
 * */

public class Utils {

    public static void showSoftInput(Context context, final View view) {
        final InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        view.requestFocus();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
            }
        }, 200);
    }

    public static void hideSoftInput(Context context, View view) {
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}
