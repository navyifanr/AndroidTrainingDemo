package cn.cfanr.complexlistviewdemo.view.viewholder;

import android.view.View;
import android.widget.RelativeLayout;

import cn.cfanr.complexlistviewdemo.R;


/**
 * Created by cfanr on 2015/12/5.
 */
public class SignMallHolder{
    RelativeLayout rlSign;
    RelativeLayout rlMall;
    public SignMallHolder(View convertView){
        if(convertView!=null){
            rlSign=(RelativeLayout)convertView.findViewById(R.id.rl_home_sign);
            rlMall=(RelativeLayout)convertView.findViewById(R.id.rl_home_mall);
        }
    }
}
