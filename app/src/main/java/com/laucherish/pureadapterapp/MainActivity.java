package com.laucherish.pureadapterapp;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;

import com.laucherish.pureadapter.PureAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private int mRowNumber;
    private int mPageIndex;
    private MyAdapter myAdapter;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setData(1);
                    }
                }, 3000);
            }
        });
        mRefreshLayout.setRefreshing(true);

        mRowNumber = 20;
        myAdapter = new MyAdapter();
        View headerView = LayoutInflater.from(this).inflate(R.layout.view_my_header, null);
        View footerView = LayoutInflater.from(this).inflate(R.layout.view_my_footer, null);
        myAdapter.addHeaderView(headerView);
        myAdapter.addFooterView(footerView);
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
                }, 2000);

            }
        }, mRecyclerView);

        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                setData(1);
            }
        }, 3000);
    }

    private void setData(int page) {
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
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
