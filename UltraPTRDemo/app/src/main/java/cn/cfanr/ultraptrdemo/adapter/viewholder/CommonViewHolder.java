package cn.cfanr.ultraptrdemo.adapter.viewholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.cfanr.ultraptrdemo.utils.ImageUtils;

/**
 * @author xifan
 * @time 2016/5/17
 * @desc
 */
public class CommonViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;

    private CommonViewHolder(Context context, ViewGroup parent, int layoutId,
                             int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    /**
     * 获取一个ViewHolder对象
     */
    public static CommonViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        CommonViewHolder holder = null;
        if (convertView == null) {
            holder = new CommonViewHolder(context, parent, layoutId, position);
        } else {
            holder = (CommonViewHolder) convertView.getTag();
            holder.mPosition = position;
        }
        return holder;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     */
    public CommonViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    public CommonViewHolder setText(int viewId, Spanned spanned) {
        TextView view = getView(viewId);
        view.setText(spanned);
        return this;
    }

    /**
     * 为ImageView设置图片
     */
    public CommonViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     */
    public CommonViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 为ImageView设置图片
     */
    public CommonViewHolder setImageByUrl(int viewId, String url) {
        ImageUtils.loadImage( (ImageView) getView(viewId), url);
        return this;
    }

    public int getPosition() {
        return mPosition;
    }

}
