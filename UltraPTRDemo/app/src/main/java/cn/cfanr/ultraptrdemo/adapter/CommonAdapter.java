package cn.cfanr.ultraptrdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import java.util.List;

import cn.cfanr.izhihudaily.view.viewholder.CommonViewHolder;

/**
 * @author xifan
 * @time 2016/5/17
 * @desc
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDataList;
    protected LayoutInflater mInflater;
    private int layoutId;

    public CommonAdapter(Context context, List<T> mDataList, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDataList = mDataList;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public T getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.get(mContext, convertView, parent,
                layoutId, position);
        convert(holder, getItem(position), position);
        return holder.getConvertView();
    }

    public void setData(List<T> mDataList){
        this.mDataList.clear();
        this.mDataList.addAll(mDataList);
        notifyDataSetChanged();
    }

    public abstract void convert(CommonViewHolder holder, T t, int position);

    public void updateSingleRow(AbsListView listView, int viewId) {
        if (listView != null) {
            int start = listView.getFirstVisiblePosition();
            for (int i = start, j = listView.getLastVisiblePosition(); i <= j; i++)
                if (viewId == ((View)listView.getItemAtPosition(i)).getId()) {
                    View view = listView.getChildAt(i - start);
                    getView(i, view, listView);
                    break;
                }
        }
    }

    public void updateItemAtPosition(AbsListView absListView, int position) {
        int visiblePosition = absListView.getFirstVisiblePosition();
        View view = absListView.getChildAt(position - visiblePosition);
        absListView.getAdapter().getView(position, view, absListView);
    }
}