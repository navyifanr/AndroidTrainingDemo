package cn.cfanr.complexlistviewdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import cn.cfanr.complexlistviewdemo.R;
import cn.cfanr.complexlistviewdemo.model.HomeItem;
import cn.cfanr.complexlistviewdemo.view.viewholder.AdHolder;
import cn.cfanr.complexlistviewdemo.view.viewholder.MealShowHolder;
import cn.cfanr.complexlistviewdemo.view.viewholder.MenuHolder;
import cn.cfanr.complexlistviewdemo.view.viewholder.SignMallHolder;
import cn.cfanr.complexlistviewdemo.view.viewholder.SpecialHolder;
import cn.cfanr.complexlistviewdemo.view.viewholder.TagHolder;
import cn.cfanr.complexlistviewdemo.view.viewholder.TalentShowHolder;


/**
 * Created by cfanr on 2015/12/4.
 */
public class HomeAdapter extends BaseAdapter{
    private Context context;
    private List<HomeItem> homeItemList;
    private final static int SIGN_MALL=0;
    private final static int TAG=1;
    private final static int SPECIAL=2;
    private final static int AD=3;
    private final static int MENU=4;
    private final static int MEAL_SHOW=5;
    private final static int TALENT_SHOW=6;

    public HomeAdapter(Context context, List<HomeItem> homeItemList){
        this.context=context;
        this.homeItemList=homeItemList;
    }

    @Override
    public int getCount(){
        return homeItemList.size();          //头部4个，广告位3个
    }

    @Override
    public Object getItem(int position){
        return homeItemList== null ? null : homeItemList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup){
        HomeItem homeItem=homeItemList.get(position);
        LayoutInflater inflater=LayoutInflater.from(context);
        SignMallHolder signMallHolder;
        TagHolder tagHolder;
        SpecialHolder specialHolder;
        MenuHolder menuHolder;
        AdHolder adHolder;
        MealShowHolder mealShowHolder;
        TalentShowHolder talentShowHolder;
        int type=homeItem.getItemType().getValue();
        switch(type){
            case SIGN_MALL:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_sign_mall,null);
                    signMallHolder=new SignMallHolder(convertView);
                    convertView.setTag(signMallHolder);
                }else{
                    signMallHolder=(SignMallHolder)convertView.getTag();
                }
                break;
            case TAG:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_tag,null);
                    tagHolder=new TagHolder(convertView);
                    convertView.setTag(tagHolder);
                }else{
                    tagHolder=(TagHolder)convertView.getTag();
                }
                tagHolder.refreshUI(homeItem);
                break;
            case SPECIAL:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_special,null);
                    specialHolder=new SpecialHolder(convertView);
                    convertView.setTag(specialHolder);
                }else{
                    specialHolder=(SpecialHolder)convertView.getTag();
                }
                specialHolder.refreshUI(homeItem);
                break;
            case AD:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_ad,null);
                    adHolder=new AdHolder(context,convertView);
                    convertView.setTag(adHolder);
                }else{
                    adHolder=(AdHolder)convertView.getTag();
                }
                adHolder.setViewPager(homeItem);
                break;
            case MENU:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_menu,null);
                    menuHolder=new MenuHolder(convertView);
                    convertView.setTag(menuHolder);
                }else{
                    menuHolder=(MenuHolder)convertView.getTag();
                }
                menuHolder.refreshUI(homeItem);
                break;
            case MEAL_SHOW:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_meal_show,null);
                    mealShowHolder=new MealShowHolder(context,convertView);
                    convertView.setTag(mealShowHolder);
                }else{
                    mealShowHolder=(MealShowHolder)convertView.getTag();
                }
                mealShowHolder.setViewPager(homeItem);
                break;
            case TALENT_SHOW:
                if(convertView==null){
                    convertView=inflater.inflate(R.layout.view_home_talent,null);
                    talentShowHolder=new TalentShowHolder(context,convertView);
                    convertView.setTag(talentShowHolder);
                }else{
                    talentShowHolder=(TalentShowHolder)convertView.getTag();
                }
                talentShowHolder.initView(homeItem);
                break;
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position){
        if (homeItemList!= null && position < homeItemList.size()) {
            return homeItemList.get(position).getItemType().getValue();
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount(){
        return 7;
    }
}
