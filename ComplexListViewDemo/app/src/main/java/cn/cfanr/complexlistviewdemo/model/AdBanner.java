package cn.cfanr.complexlistviewdemo.model;

/**
 * Created by cfanr on 2015/12/4.
 */
public class AdBanner{
    private String[] titles;
    private int[] imgIds;

    public String[] getTitle(){
        return titles;
    }

    public void setTitle(String[] titles){
        this.titles=titles;
    }

    public int[] getImgIds(){
        return imgIds;
    }

    public void setImgIds(int[] imgIds){
        this.imgIds=imgIds;
    }
}
