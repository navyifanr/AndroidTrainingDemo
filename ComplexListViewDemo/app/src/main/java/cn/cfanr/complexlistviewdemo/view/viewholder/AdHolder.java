package cn.cfanr.complexlistviewdemo.view.viewholder;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;

import cn.cfanr.complexlistviewdemo.R;
import cn.cfanr.complexlistviewdemo.adapter.ImageAdapter;
import cn.cfanr.complexlistviewdemo.model.Ad;
import cn.cfanr.complexlistviewdemo.model.HomeItem;
import cn.cfanr.complexlistviewdemo.utils.ToastUtil;

/**
 * Created by cfanr on 2015/12/5.
 */
public class AdHolder{
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

    Context context;
    int currentItem=0;
    ViewPager mViewPager;
    ImageAdapter imgAdapter;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
            if(handler.hasMessages(MSG_UPDATE_IMAGE)){
                handler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch(msg.what){
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    mViewPager.setCurrentItem(currentItem);
                    //准备下次播放
                    handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    //只要不发送消息就暂停了
                    break;
                case MSG_BREAK_SILENT:
                    handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
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
    public AdHolder(Context context,View convertView){
        this.context=context;
        if(convertView!=null){
            mViewPager=(ViewPager)convertView.findViewById(R.id.viewpager_ad);
        }
    }
    public void setViewPager(HomeItem homeItem){
        final Ad ad=homeItem.getAd();
        if(imgAdapter==null){
            imgAdapter=new ImageAdapter(context, ad.getImgIds());
            mViewPager.setAdapter(imgAdapter);
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
                }
                @Override
                public void onPageSelected(int position){
                    handler.sendMessage(Message.obtain(handler, MSG_PAGE_CHANGED, position, 0));
                }
                @Override
                public void onPageScrollStateChanged(int state){
                    switch(state){
                        case ViewPager.SCROLL_STATE_DRAGGING:
                            handler.sendEmptyMessage(MSG_KEEP_SILENT);
                            break;
                        case ViewPager.SCROLL_STATE_IDLE:
                            handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                            break;
                        default:
                            break;
                    }
                }
            });
            mViewPager.setCurrentItem(Integer.MAX_VALUE/2);//默认在中间，使用户看不到边界
            //开始轮播效果
            handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);

            imgAdapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener(){
                @Override
                public void onItemClick(View view, int position){
                    ToastUtil.show("i am ad "+position);
                }
            });
        }
    }
}
