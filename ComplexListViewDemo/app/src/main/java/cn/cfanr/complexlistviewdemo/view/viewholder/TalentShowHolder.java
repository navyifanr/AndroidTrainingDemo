package cn.cfanr.complexlistviewdemo.view.viewholder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import cn.cfanr.complexlistviewdemo.R;
import cn.cfanr.complexlistviewdemo.adapter.TalentShowAdapter;
import cn.cfanr.complexlistviewdemo.model.HomeItem;
import cn.cfanr.complexlistviewdemo.model.TalentShow;
import cn.cfanr.complexlistviewdemo.utils.ToastUtil;

/**
 * Created by cfanr on 2015/12/5.
 */
public class TalentShowHolder{
    Context context;
    RecyclerView mRecyclerView;
    TalentShowAdapter mAdapter;

    public TalentShowHolder(Context context,View convertView){
        this.context=context;
        if(convertView!=null){
            mRecyclerView=(RecyclerView)convertView.findViewById(R.id.recycler_view_talent);
        }
    }

    public void initView(HomeItem homeItem){
        List<TalentShow> talentShowList=homeItem.getTalentShowList();
        mAdapter=new TalentShowAdapter(context,talentShowList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new TalentShowAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position){
                ToastUtil.show("i am talent "+position);
            }
        });
    }
}
