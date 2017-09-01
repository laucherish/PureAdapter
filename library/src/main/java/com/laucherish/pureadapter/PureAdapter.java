package com.laucherish.pureadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laucherish on 2017/8/31.
 */

public class PureAdapter<T> extends RecyclerView.Adapter<PureViewHolder> {

    protected Context mContext;
    protected List<T> mData;
    protected int mLayoutResId;

    public PureAdapter() {

    }

    public PureAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : data;
        if (layoutResId != 0) {
            this.mLayoutResId = layoutResId;
        }
    }

    public PureAdapter(@Nullable List<T> data) {
        this(0, data);
    }

    public PureAdapter(@LayoutRes int layoutResId) {
        this(layoutResId, null);
    }

    public void setNewData(List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : data;
        notifyDataSetChanged();
    }

    @Override
    public PureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PureViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public interface RequestLoadMoreListener {

        void onLoadMoreRequested();

    }
}
