package com.dalong.elmbusiness.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zhouweilong on 2016/12/27.
 */

public abstract class MyBaseAdapter<T>  extends BaseAdapter {

    public List<T> dataList;
    public Context mContext;

    public MyBaseAdapter(List<T> dataList, Context mContext) {
        this.dataList = dataList;
        this.mContext = mContext;
    }

    public MyBaseAdapter(List<T> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
