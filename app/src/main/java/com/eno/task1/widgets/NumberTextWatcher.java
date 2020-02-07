package com.eno.task1.widgets;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/*
 * Format edittext with #.## format
 * @author Sandeep Emekar
 * @since 06/02/2020
 *
 * */
public class NumberTextWatcher implements TextWatcher {

    private EditText editText;
    String lastDigit = "";

    public NumberTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (!editText.getText().toString().isEmpty()) {
            lastDigit = String.valueOf(editText.getText().toString().charAt(editText.getText().toString().length() - 1));

        }
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        try {
            if (!lastDigit.contains(".")) {
                if (editText.getText().toString().length() == 1) {
                    editText.setText(editText.getText().toString() + ".");
                    editText.setSelection(editText.getText().toString().length());
                    lastDigit = String.valueOf(editText.getText().toString().charAt(editText.getText().toString().length() - 1));
                } else if (editText.getText().toString().length() == 2 && !editText.getText().toString().contains(".")) {
                    String str = s.toString().substring(0, s.toString().length() - 1) + "." + s.toString().substring(s.length() - 1, s.length());
                    editText.setText(str);
                    editText.setSelection(editText.getText().toString().length());
                    lastDigit = String.valueOf(editText.getText().toString().charAt(editText.getText().toString().length() - 1));
                }
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

}