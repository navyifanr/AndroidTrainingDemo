package cn.cfanr.complexlistviewdemo.view.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.cfanr.complexlistviewdemo.R;
import cn.cfanr.complexlistviewdemo.model.HomeItem;
import cn.cfanr.complexlistviewdemo.model.MenuPo;
import cn.cfanr.complexlistviewdemo.utils.ToastUtil;

/**
 * Created by cfanr on 2015/12/5.
 */
public class MenuHolder{

    RelativeLayout rlMenu1;
    RelativeLayout rlMenu2;

    ImageView ivImg1,ivImg2;
    TextView tvName1,tvName2;
    TextView tvIntro1,tvIntro2;
    TextView tvComment1,tvComment2;
    TextView tvLike1,tvLike2;
    public MenuHolder(View convertView){
        if(convertView!=null){
            rlMenu1=(RelativeLayout)convertView.findViewById(R.id.i_menu_one);
            rlMenu2=(RelativeLayout)convertView.findViewById(R.id.i_menu_two);
            ivImg1=(ImageView) rlMenu1.findViewById(R.id.iv_menu_img);
            tvName1=(TextView) rlMenu1.findViewById(R.id.tv_menu_meal_name);
            tvIntro1=(TextView) rlMenu1.findViewById(R.id.tv_menu_meal_intro);
            tvComment1=(TextView) rlMenu1.findViewById(R.id.tv_menu_comment);
            tvLike1=(TextView) rlMenu1.findViewById(R.id.tv_menu_like);

            ivImg2=(ImageView) rlMenu2.findViewById(R.id.iv_menu_img);
            tvName2=(TextView) rlMenu2.findViewById(R.id.tv_menu_meal_name);
            tvIntro2=(TextView) rlMenu2.findViewById(R.id.tv_menu_meal_intro);
            tvComment2=(TextView) rlMenu2.findViewById(R.id.tv_menu_comment);
            tvLike2=(TextView) rlMenu2.findViewById(R.id.tv_menu_like);

            rlMenu1.setOnClickListener(onMenuClickListener);
            rlMenu2.setOnClickListener(onMenuClickListener);
        }
    }

    View.OnClickListener onMenuClickListener=new View.OnClickListener(){
        @Override
        public void onClick(View view){
            if(view.getId()==R.id.i_menu_one){
                ToastUtil.show("i am menu one");
            }else if(view.getId()==R.id.i_menu_two){
                ToastUtil.show("i am menu two");
            }
        }
    };

    public void refreshUI(HomeItem homeItem){
        MenuPo[] menuPos=homeItem.getMenuPos();
        tvName1.setText(menuPos[0].getDishName());
        tvIntro1.setText(menuPos[0].getDishIntro());
        tvComment1.setText(menuPos[0].getCommentNum());
        tvLike1.setText(menuPos[0].getLikeNum());

        if(menuPos[1]==null){
            rlMenu2.setVisibility(View.INVISIBLE);
            return;
        }else{
            rlMenu2.setVisibility(View.VISIBLE);
            tvName2.setText(menuPos[1].getDishName());
            tvIntro2.setText(menuPos[1].getDishIntro());
            tvComment2.setText(menuPos[1].getCommentNum());
            tvLike2.setText(menuPos[1].getLikeNum());
        }
    }
}
