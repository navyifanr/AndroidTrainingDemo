package cn.cfanr.coordinatorlayoutdemo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.cfanr.coordinatorlayoutdemo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        findViewById(R.id.btn_main_scrolling).setOnClickListener(this);
        findViewById(R.id.btn_main_zhihu_home).setOnClickListener(this);
        findViewById(R.id.btn_main_zhihu_daily).setOnClickListener(this);
        findViewById(R.id.btn_main_bottom_sheet).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_main_scrolling:
                startActivity(new Intent(this, ScrollingActivity.class));
                break;
            case R.id.btn_main_zhihu_home:
                startActivity(new Intent(this, ZhihuActivity.class));
                break;
            case R.id.btn_main_zhihu_daily:
                startActivity(new Intent(this, ZhihuDailyActivity.class));
                break;
            case R.id.btn_main_bottom_sheet:
                startActivity(new Intent(this, BottomSheetActivity.class));
                break;
            default:
                break;
        }
    }

}
