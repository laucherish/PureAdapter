package com.laucherish.pureadapterapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.laucherish.pureadapter.PureAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private int mRowNumber;
    private int mPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRowNumber = 20;
        MyAdapter myAdapter = new MyAdapter();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter.setOnLoadMoreListener(new PureAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData(mPageIndex);
            }
        }, mRecyclerView);

        myAdapter.setNewData(getData(1));
    }

    private List<String> getData(int page){
        mPageIndex = page;
        List<String> list = new ArrayList<>();
        for (int i=0;i<mRowNumber;i++) {
            list.add("This is page:" + page + ", row:" + i);
        }
        mPageIndex++;
        return list;
    }
}
