package com.eno.task1;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/*
 *
 * Simple class to achieve the requirements of Task 1
 * @author Sandeep Emekar
 * @since 06/02/2020
 *
 * */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final double MIN_VALUE_REQUIRED = 1.99;
    private static final double MAX_VALUE_REQUIRED = 5.99;
    TextView tvText;
    EditText edtValue;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvText = findViewById(R.id.tvText);
        edtValue = findViewById(R.id.edtValue);
        btnStart = findViewById(R.id.btnStart);


        /*TextWatcher to validate X.XX number format*/
        TextWatcher tw = new NumberTextWatcher(edtValue);
        edtValue.addTextChangedListener(tw);
        edtValue.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (!edtValue.getText().toString().isEmpty()) {
                        if (validateNumber())
                            checkNumberRange(Double.valueOf(edtValue.getText().toString()));
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.please_enter_a_valid_number), Toast.LENGTH_LONG).show();
                    }
                }
                return false;
            }
        });

        /*OnClickListeners*/
        btnStart.setOnClickListener(this);
    }

    /*
     *
     * Check if given number is in X.XX format only
     * @author Sandeep Emekar
     * @since 06/02/2020
     *
     * */
    private boolean validateNumber() {
        if ((!edtValue.getText().toString().isEmpty()) && (edtValue.getText().toString().matches("\\d+(\\.\\d{2})?"))) {
            return true;
        } else {
            Toast.makeText(this, getString(R.string.please_enter_number_in_format), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /*
     *
     * Check if given number matches minimum and maximum requirement
     * @author Sandeep Emekar
     * @since 06/02/2020
     *
     * */
    private void checkNumberRange(double value) {
        if (value < MIN_VALUE_REQUIRED) {
            DialogManager.showErrorDialog(this, getString(R.string.error), getString(R.string.value_is_less_than_one_nine_nine));
        } else if (value > MAX_VALUE_REQUIRED) {
            DialogManager.showErrorDialog(this, getString(R.string.error), getString(R.string.value_is_greater_than_five_nine_nine));
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnStart) {
            setEditTextVisible();
        }
    }

    /*
     *
     * Hide TextView and show EditText with softinput focus when btnStart is tapped
     * @author Sandeep Emekar
     * @since 06/02/2020
     *
     * */
    private void setEditTextVisible() {
        if (edtValue.getVisibility() == View.GONE) {
            tvText.setVisibility(View.GONE);
            edtValue.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.GONE);
            Utils.showSoftInput(this, edtValue);
        }
    }
}
