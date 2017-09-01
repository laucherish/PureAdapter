package com.laucherish.pureadapterapp;

import com.laucherish.pureadapter.PureAdapter;
import com.laucherish.pureadapter.PureViewHolder;

/**
 * Created by laucherish on 2017/9/1.
 */

public class MyAdapter extends PureAdapter<String> {

    public MyAdapter() {
        super(R.layout.item_my_adapter);
    }

    @Override
    protected void convert(PureViewHolder helper, String item) {
        helper.setText(R.id.tv_title, item);
    }
}
