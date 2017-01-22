package cn.cfanr.ultraptrdemo.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import cn.cfanr.ultraptrdemo.R;
import cn.cfanr.ultraptrdemo.base.BaseActivity;
import cn.cfanr.ultraptrdemo.fragment.custom.CustomWithListView;

public class ContentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_content;
    }

    @Override
    public void initViews() {
        Bundle bundle=getIntent().getExtras();
        String title=bundle.getString("title");
        setTitle(title);
        FragmentManager fm =getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frame_layout_content, CustomWithListView.newInstance()).commit();
    }

    @Override
    public void initEvent() {

    }


}
