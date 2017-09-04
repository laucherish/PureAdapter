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
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRowNumber = 20;
        myAdapter = new MyAdapter();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setAdapter(myAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter.setOnLoadMoreListener(new PureAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setData(mPageIndex);
                    }
                }, 1000);

            }
        }, mRecyclerView);

        setData(1);
    }

    private void setData(int page) {
        mPageIndex = page;
        List<String> list = new ArrayList<>();

        if (page < 5) {
            for (int i = 0; i < mRowNumber; i++) {
                list.add("This is page:" + page + ", row:" + i);
            }
        }

        if (page == 1) {
            myAdapter.setNewData(list);
        } else {
            myAdapter.addData(list);
            myAdapter.loadMoreComplete();
        }

        if (list.isEmpty()) {
            myAdapter.loadMoreEnd();
        }
        mPageIndex++;
    }
}
