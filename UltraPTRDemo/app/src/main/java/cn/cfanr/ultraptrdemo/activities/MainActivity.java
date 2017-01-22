package cn.cfanr.ultraptrdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.cfanr.ultraptrdemo.R;
import cn.cfanr.ultraptrdemo.adapter.CommonAdapter;
import cn.cfanr.ultraptrdemo.adapter.viewholder.CommonViewHolder;
import cn.cfanr.ultraptrdemo.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private GridView mClassicGridView;
    private GridView mCustomGridView;

    private String[] classicTitles ={
            "包含ListView",
            "包含ScrollView",
            "包含GridView",
            "包含WebView",
            "释放刷新",
            "下拉刷新",
            "自动刷新"
    };

    private String[] customTitles={
            "包含ListView",
            "包含ScrollView",
            "包含GridView",
            "包含WebView",
            "释放刷新",
            "下拉刷新",
            "自动刷新"
    };

    private List<String> classicTitleList=new ArrayList<>();
    private List<String> customTitleList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        mClassicGridView =$(R.id.grid_view_classic);
        mCustomGridView=$(R.id.grid_view_custom);

        setData();
        mClassicGridView.setAdapter(new CommonAdapter<String>(getActivity(), classicTitleList, android.R.layout.simple_list_item_1) {

            @Override
            public void convert(CommonViewHolder holder, String str, int position) {
                TextView textView=holder.getView(android.R.id.text1);
                textView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                textView.setTextColor(getResources().getColor(R.color.white));
                textView.setText(str);
            }
        });
        mCustomGridView.setAdapter(new CommonAdapter<String>(getActivity(), customTitleList, android.R.layout.simple_list_item_1) {
            @Override
            public void convert(CommonViewHolder holder, String str, int position) {
                TextView textView=holder.getView(android.R.id.text1);
                textView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                textView.setTextColor(getResources().getColor(R.color.white));
                textView.setText(str);
            }
        });
    }

    @Override
    public void initEvent() {
        mClassicGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(), ContentActivity.class);
                intent.putExtra("title", customTitleList.get(position));
                startActivity(intent);
            }
        });

        mCustomGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(), ContentActivity.class);
                intent.putExtra("title", customTitleList.get(position));
                startActivity(intent);
            }
        });
    }

    private void setData(){
        for(String title: classicTitles){
            classicTitleList.add(title);
        }
        for(String title: customTitles){
            customTitleList.add(title);
        }
    }

}

