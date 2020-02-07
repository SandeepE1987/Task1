package com.eno.task1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.eno.task1.adapters.ItemRVAdapter;
import com.eno.task1.models.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityTask2 extends AppCompatActivity {

    private static final int ROW_COUNT = 4;
    ItemRVAdapter itemRVAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);
        recyclerView = findViewById(R.id.recyclerView);

        setListAdapter();
    }

    private void setListAdapter() {
        itemRVAdapter = new ItemRVAdapter(this, getListItems(ROW_COUNT));
        recyclerView.setAdapter(itemRVAdapter);
    }

    private List<ItemModel> getListItems(int rowCount) {
        List<ItemModel> itemModelList = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            itemModelList.add(new ItemModel());
        }
        return itemModelList;
    }
}
