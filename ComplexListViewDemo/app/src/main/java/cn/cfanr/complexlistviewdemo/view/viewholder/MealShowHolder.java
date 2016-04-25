package cn.cfanr.complexlistviewdemo.view.viewholder;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import cn.cfanr.complexlistviewdemo.R;
import cn.cfanr.complexlistviewdemo.adapter.ImageAdapter;
import cn.cfanr.complexlistviewdemo.model.HomeItem;
import cn.cfanr.complexlistviewdemo.model.MealShow;
import cn.cfanr.complexlistviewdemo.utils.DisplayTool;
import cn.cfanr.complexlistviewdemo.utils.ToastUtil;
import cn.cfanr.complexlistviewdemo.view.CircleImageView;


/**
 * Created by cfanr on 2015/12/5.
 */
public class MealShowHolder{
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
    DisplayTool displayTool;
    int currentItem=0;
    RelativeLayout rlItem;
    ViewPager mViewPager;
    CircleImageView ivAvatar;
    TextView tvUserName;
    TextView tvCommentNum;
    TextView tvLikeNum;
    TextView tvTitle;
    LinearLayout llDots;
    ImageAdapter imgAdapter;
    private int preDotPosition = 0; //上一个被选中的小圆点的索引，默认值为0
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
    public MealShowHolder(Context context,View convertView){
        this.context=context;
        displayTool=new DisplayTool(context);
        if(convertView!=null){
            rlItem=(RelativeLayout)convertView.findViewById(R.id.rl_meal_show_item);
            mViewPager=(ViewPager)convertView.findViewById(R.id.viewpager_meal_show);
            ivAvatar=(CircleImageView)convertView.findViewById(R.id.circle_iv_meal_show_avatar);
            tvUserName=(TextView)convertView.findViewById(R.id.tv_meal_show_user_name);
            tvCommentNum=(TextView)convertView.findViewById(R.id.tv_meal_show_comment);
            tvLikeNum=(TextView)convertView.findViewById(R.id.tv_meal_show_like);
            tvTitle=(TextView)convertView.findViewById(R.id.tv_meal_show_title);
            llDots=(LinearLayout)convertView.findViewById(R.id.ll_meal_show_dots);
        }
    }

    public void setViewPager(HomeItem homeItem){
        final List<MealShow> mealShowList=homeItem.getMealShowList();
        final int length=mealShowList.size();
        int[] imgIds=new int[length];

        if(imgAdapter==null){   //避免重复初始化ViewPager和addView
            View dot = null;
            LinearLayout.LayoutParams params = null;
            for(int i=0;i<length;i++){
                imgIds[i]=mealShowList.get(i).getImgId();
                dot=new View(context);
                dot.setBackgroundResource(R.drawable.dot_bg_selector);
                params = new LinearLayout.LayoutParams(displayTool.dip2px(5), displayTool.dip2px(5));
                params.leftMargin = displayTool.dip2px(10);
                dot.setEnabled(false);
                dot.setLayoutParams(params);
                llDots.addView(dot); // 向线性布局中添加"点"
            }
            imgAdapter=new ImageAdapter(context, imgIds);
            mViewPager.setAdapter(imgAdapter);
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
                }

                @Override
                public void onPageSelected(final int position){
                    handler.sendMessage(Message.obtain(handler, MSG_PAGE_CHANGED, position, 0));
                    int newPosition=position%length;
                    MealShow mealShow=mealShowList.get(newPosition);
                    tvUserName.setText(mealShow.getUserName());
                    tvCommentNum.setText(mealShow.getCommentNum());
                    tvLikeNum.setText(mealShow.getLikeNum());
                    tvTitle.setText(mealShow.getTitle());
                    // 把上一个点设置为被选中
                    llDots.getChildAt(preDotPosition).setEnabled(false);
                    llDots.getChildAt(newPosition).setEnabled(true);
                    preDotPosition=newPosition;
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
            //默认设置
            mViewPager.setCurrentItem(0);
            MealShow ms=mealShowList.get(0);
//                ivAvatar.s
            tvLikeNum.setText(ms.getLikeNum());
            tvCommentNum.setText(ms.getCommentNum());
            tvUserName.setText(ms.getUserName());
            tvTitle.setText(ms.getTitle());
            llDots.getChildAt(0).setEnabled(true);
            //开始轮播效果
            handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);

            imgAdapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener(){
                @Override
                public void onItemClick(View view, int position){
                    ToastUtil.show("i am item "+position);
                }
            });
        }
    }
}
