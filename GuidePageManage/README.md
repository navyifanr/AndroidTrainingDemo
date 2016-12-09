##  Android用建造者模式实现一个新功能引导页的功能

------------------------------

最近每次版本更新都会在UI变动或加了新功能的地方加一个引导蒙层页面（新功能标记、文案和一个“知道了”的按钮），有时候一个版本会加三四个页面，原来的做法是：
1.在每个Activity的布局文件外层添加一个FrameLayout(这方法好蠢，又要嵌套一层布局)；
2.再将需要显示的引导页布局加在后面(或用include的方式);
3.写两个方法，获取和设置是否显示了该引导页的判断，存储到SharedPreferences;
4.最后在对应Activity页面添加对应控制引导页显隐的逻辑
……
每添加一个页面就要重复上面四个步骤，而且之后版本迭代需要去掉这些冗余代码时也比较麻烦，改动的地方比较多，不利于管理。
这方法太笨了，不能纯粹只为了完成功能呀，于是想了一个简单有效的方法。

![](https://raw.githubusercontent.com/navyifanr/AndroidTrainingDemo/master/GuidePageManage/image.png)

首先解决避免改动原Activity布局的问题，只要通过findViewById(android.R.id.content)获取Activity根布局下的FrameLayout, 再将需要添加的引导页布局addView(view)进入就可以了
```
FrameLayout rootLayout = (FrameLayout) activity.findViewById(android.R.id.content);
View layoutView = View.inflate(activity, layoutId, null);
rootLayout.add(layoutView);
```
这样就解决了改动Activity布局的问题，同时也将引导页的内容单独放在一个Layout文件（说得好像是废话，这不是必须的吗，不过我还真看过有人将所有布局代码都放在Activity的布局文件的 o(╯□╰)o）

然后后面的优化就好办了。将存储到SharedPreferences引导页是否已展示的方法增加一个pageTag的参数，通过外部使用时传入，这样就避免了每个页面都写同类型的方法的问题了。

最后将上面所说的方法封装在一个类GuidePage，使用时传入activity, layoutId, knowViewId(知道了按钮)和pageTag即可，写好这个工具类后，之后再需要增加引导页时只需要：
1.写一个引导页面的布局；
2.在对应的Activity页面调用这个工具方法即可；
```
private void apply(@LayoutRes int layoutId, @IdRes int knowViewId, String pageTag) {
    FrameLayout rootLayout = (FrameLayout) activity.findViewById(android.R.id.content);
    View layoutView = View.inflate(activity, layoutId, null);
    rootLayout.addView(layoutView);
    layoutView.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    });
    layoutView.findViewById(knowViewId).setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            rootLayout.removeView(layoutView);
            SpUtil.setHasShowedGuidePage(activity, pageTag, true);
        }
    });
}
//调用时，直接在Activity写
if(!SpUtil.hasShowedGuidePage()){
    new GuidePage(this).apply(R.layout.view_home_guide_page, R.id.home_guide_page_know, pageTag);
}
```

减少了大量重复代码，不用修改原来的布局文件，后期删除也比较方便，简单吧  -_-

理论上，这样就完了，不过程序员比较喜欢折腾，这样直接在构造器写四个参数好像不太好看吧，好像可以用建造者模式改下，于是就开始了将简单的事情搞复杂的道路-_-

-------------------------------分割线---------------------------

期待这样调用就可以了
```
new GuidePage.Builder(this)
      .setLayoutId(R.layout.view_home_guide_page)
      .setKnowViewId(R.id.home_guide_page_know)
      .setPageTag(pageTag)
      .builder()
      .apply();
```
建造者模式有两种写法，一种是有设计者的（有序），一种是无设计者的（无序），AlertDialog是无设计者的方式，简单一点，比较喜欢这种连写的方式。不记得建造者模式的可以看篇文章回忆下：[[Android] 设计模式-建造者模式](http://blog.qiji.tech/archives/5886)

建一个GuidePage的类，里面有个Builder的内部类，通过内部类设置activity, layoutId, knowViewId等参数。存储引导页是否已展示的方法好像可以直接写在GuidePage，就不用在外部判断了，不过这样要执行一些无用的代码，不适合；还有pageTag好像可以直接用activity.getClass().getSimpleName(), 也不用传这个值了，不过如果同一个Activity有两个引导页(如不同Fragment需要不同引导页)就不适用了，还是只能通过参数传入好点

好吧，这么简单，就不多说了，直接上代码:
```
public class GuidePage {
    private int layoutId;
    private int knowViewId;
    private String pageTag;
    private boolean mCancel = false;

    private Activity activity;
    private FrameLayout rootLayout;
    private View layoutView;
    //设置为 protected或private, 不被外部直接调用
    protected GuidePage(){
    }

    public static class Builder{
        private GuidePage guidePage = new GuidePage();

        public Builder(Activity activity){
            guidePage.activity = activity;
        }

        public Builder setLayoutId(@LayoutRes int layoutId){
            guidePage.layoutId = layoutId;
            return this;
        }

        public Builder setKnowViewId(@IdRes int knowViewId){
            guidePage.knowViewId = knowViewId;
            return this;
        }

        /**
         * 引导唯一的标记，用作存储到SharedPreferences的key值，不同引导页必须不一样
         * @param pageTag
         * @return
         */
        public Builder setPageTag(String pageTag){
            guidePage.pageTag = pageTag;
            return this;
        }

        public Builder setCloseOnTouchOutside(boolean cancel){
            guidePage.mCancel = cancel;
            return this;
        }

        public GuidePage builder(){
            if(TextUtils.isEmpty(guidePage.pageTag)){
                throw new RuntimeException("the guide page must set page tag");
            }
            guidePage.setLayoutView();
            guidePage.setKnowEvent();
            guidePage.setCloseOnTouchOutside();
            return guidePage;
        }
    }

    public void setLayoutView(){
        rootLayout = (FrameLayout) activity.findViewById(android.R.id.content);
        layoutView = View.inflate(activity, layoutId, null);
    }

    public void setKnowEvent(){
        if(layoutView!=null) {
            layoutView.findViewById(knowViewId).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancel();
                }
            });
        }
    }

    public void setCloseOnTouchOutside(){
        if(layoutView == null)
            return;
        layoutView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(mCancel){
                    cancel();
                }
                return true;  //消费事件，不下发
            }
        });
    }

    public void apply(){
        rootLayout.addView(layoutView);
    }

    public void cancel(){
        if(rootLayout!=null && layoutView!=null) {
            rootLayout.removeView(layoutView);
            GuidePageManager.setHasShowedGuidePage(activity, pageTag, true);
        }
    }
}
```
下面是判断页面是否显示引导页的类，或者用来定义一些静态的pageTag也行
```
public class GuidePageManager {

    private GuidePageManager(){
    }
    /**
     * @param activity
     * @param pageKey 使用时，传入的值必须和GuidePage设置的值一样
     * @return
     */
    public static boolean hasNotShowed(Activity activity, String pageKey){
        return !hasShowedGuidePage(activity, pageKey);
    }

    public static void setHasShowedGuidePage(Context context, String key, boolean hasShowed){
        SharedPreferences settings= context.getSharedPreferences(context.getPackageName(), 0);
        SharedPreferences.Editor editor=settings.edit();
        editor.putBoolean(key, hasShowed);
        editor.commit();
    }

    private static boolean hasShowedGuidePage(Context context, String key){
        SharedPreferences settings=context.getSharedPreferences(context.getPackageName(), 0);
        boolean value=settings.getBoolean(key, false);
        return value;
    }
}
```
最后，使用方法
```
if(GuidePageManager.hasNotShowed(this, MainActivity.class.getSimpleName())){
    new GuidePage.Builder(this)
            .setLayoutId(R.layout.view_home_guide_page)
            .setKnowViewId(R.id.btn_home_act_enter_know)
            .setPageTag(MainActivity.class.getSimpleName())
            .builder()
            .apply();
}
```

Github代码: [GuidePageManage](https://github.com/navyifanr/AndroidTrainingDemo/tree/master/GuidePageManage)

完了。
