package cn.cfanr.complexlistviewdemo.view.viewholder;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import cn.cfanr.complexlistviewdemo.R;
import cn.cfanr.complexlistviewdemo.adapter.ImageAdapter;
import cn.cfanr.complexlistviewdemo.model.AdBanner;
import cn.cfanr.complexlistviewdemo.utils.ToastUtil;

/**
 * Created by cfanr on 2015/12/5.
 */
public class AdBannerHeader{
    Context context;
    public View view;
    ViewPager mViewPager;
    TextView tvTitle;
    ImageAdapter imgAdapter;

    private int currentItem=0;
    //请求更新显示的View
    protected static final int MSG_UPDATE_IMAGE=1;
    //请求暂停轮播
    protected static final int MSG_KEEP_SILENT=2;
    //请求恢复轮播
    protected static final int MSG_BREAK_SILENT=3;
    /**
     * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
     * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
     * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
     */
    protected static final int MSG_PAGE_CHANGED=4;
    //轮播间隔时间
    protected static final long MSG_DELAY=3000;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
            if(mHandler.hasMessages(MSG_UPDATE_IMAGE)){
                mHandler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch(msg.what){
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    mViewPager.setCurrentItem(currentItem);
                    //准备下次播放
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    //只要不发送消息就暂停了
                    break;
                case MSG_BREAK_SILENT:
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    //记录当前的页号，避免播放的时候页面显示不正确。
                    currentItem=msg.arg1;
                    break;
                default:
                    break;
            }
        }
    };

    public AdBannerHeader(Context context,AdBanner adBanner){
        this.context=context;
        view=LayoutInflater.from(context).inflate(R.layout.view_home_ad_banner,null);
        mViewPager=(ViewPager)view.findViewById(R.id.viewpager_ad);
        tvTitle=(TextView)view.findViewById(R.id.tv_ads_title);

        setViewPager(adBanner);
    }

    private void setViewPager(final AdBanner adBanner){
        imgAdapter=new ImageAdapter(context,adBanner.getImgIds());
        imgAdapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position){
                ToastUtil.show("i am banner ad "+position);
            }
        });
        mViewPager.setAdapter(imgAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){

            }

            @Override
            public void onPageSelected(int position){
                mHandler.sendMessage(Message.obtain(mHandler, MSG_PAGE_CHANGED, position, 0));
                // 取余后的索引，得到新的page的索引
                int newPosition=position%adBanner.getImgIds().length;
                // 根据索引设置图片的描述
                tvTitle.setText(adBanner.getTitle()[newPosition]);
            }

            @Override
            public void onPageScrollStateChanged(int state){
                switch(state){
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        mHandler.sendEmptyMessage(MSG_KEEP_SILENT);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                        break;
                    default:
                        break;
                }
            }
        });

        mViewPager.setCurrentItem(0);//默认在中间，使用户看不到边界
        tvTitle.setText(adBanner.getTitle()[0]);
        //开始轮播效果
        mHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
    }
}
