package com.eno.task1;

import android.app.Activity;
import android.app.AlertDialog;

/*
 *
 * @author Sandeep Emekar
 * @since 06/02/2020
 *
 * */

public class DialogManager {

    public static void showErrorDialog(Activity activity, String titile, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.MyAlertDialogStyle);
        builder.setTitle(titile);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, null);
        builder.setIcon(android.R.drawable.stat_notify_error);
        builder.show();
    }
}
