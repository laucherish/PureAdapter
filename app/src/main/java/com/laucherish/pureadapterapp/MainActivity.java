package com.laucherish.pureadapterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private int mRowNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRowNumber = 10;
        MyAdapter myAdapter = new MyAdapter();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter.setNewData(getData(1));
    }

    private List<String> getData(int page){
        List<String> list = new ArrayList<>();
        for (int i=0;i<mRowNumber;i++) {
            list.add("This is page:" + page + ", row:" + i);
        }
        return list;
    }
}
