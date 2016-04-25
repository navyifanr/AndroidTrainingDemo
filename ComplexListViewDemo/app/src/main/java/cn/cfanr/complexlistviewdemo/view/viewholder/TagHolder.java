package cn.cfanr.complexlistviewdemo.view.viewholder;

import android.view.View;
import android.widget.TextView;

import cn.cfanr.complexlistviewdemo.R;
import cn.cfanr.complexlistviewdemo.model.HomeItem;


/**
 * Created by cfanr on 2015/12/5.
 */
public class TagHolder{
    public TextView tvType;
    public TagHolder(View convertView){
        if(convertView!=null){
            tvType=(TextView)convertView.findViewById(R.id.tv_tag_txt);
        }
    }

    public void refreshUI(HomeItem homeItem){
        tvType.setText(homeItem.getTagName());
    }
}
