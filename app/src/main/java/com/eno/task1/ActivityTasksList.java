package com.eno.task1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityTasksList extends AppCompatActivity implements View.OnClickListener {
    TextView tvTask1, tvTask2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_list);
        tvTask1 = findViewById(R.id.tvTask1);
        tvTask2 = findViewById(R.id.tvTask2);

        tvTask1.setOnClickListener(this);
        tvTask2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == tvTask1) {
            showTask1Activity();
        } else if (v == tvTask2) {
            showTask2Activity();
        }
    }

    private void showTask2Activity() {
        Intent intent = new Intent(this, ActivityTask2.class);
        startActivity(intent);
    }

    private void showTask1Activity() {
        Intent intent = new Intent(this, ActivityTask1.class);
        startActivity(intent);
    }
}
