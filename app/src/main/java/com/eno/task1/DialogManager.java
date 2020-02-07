package com.eno.task1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/*
 *
 * @author Sandeep Emekar
 * @since 06/02/2020
 *
 * */

public class DialogManager {
    static AlertDialog alertDiscardDialog;
    static AlertDialog alertErrorDialog;

    public static void showErrorDialog(Activity activity, String title, String message) {
        alertErrorDialog = new AlertDialog.Builder(activity, R.style.MyAlertDialogStyle).create();
        alertErrorDialog.setTitle(title);
        alertErrorDialog.setMessage(message);
        alertErrorDialog.setButton(DialogInterface.BUTTON_POSITIVE, activity.getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertErrorDialog.setIcon(android.R.drawable.stat_notify_error);
        if (!activity.isFinishing())
            alertErrorDialog.show();
    }

    public static void showDiscardUpdateDialog(Activity activity, String title, String message, final ConfirmationListener confirmationListener) {
        /*Patch to handle duplicate dialog shown due to RecyclerView notifying items*/
        if (alertDiscardDialog == null)
            alertDiscardDialog = new AlertDialog.Builder(activity, R.style.MyAlertDialogStyle).create();
        else {
            alertDiscardDialog.dismiss();
            alertDiscardDialog = new AlertDialog.Builder(activity, R.style.MyAlertDialogStyle).create();
        }

        alertDiscardDialog.setTitle(title);
        alertDiscardDialog.setMessage(message);
        alertDiscardDialog.setButton(DialogInterface.BUTTON_POSITIVE, activity.getString(R.string.update), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (confirmationListener != null) {
                    confirmationListener.onClickYes();
                }
            }
        });

        alertDiscardDialog.setButton(DialogInterface.BUTTON_NEGATIVE, activity.getString(R.string.discard), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (confirmationListener != null) {
                    confirmationListener.onClickNo();
                }
            }
        });

        alertDiscardDialog.setIcon(android.R.drawable.stat_notify_error);
        if (!alertDiscardDialog.isShowing() && !activity.isFinishing())
            alertDiscardDialog.show();
    }

    public interface ConfirmationListener {
        void onClickYes();

        void onClickNo();
    }
}
