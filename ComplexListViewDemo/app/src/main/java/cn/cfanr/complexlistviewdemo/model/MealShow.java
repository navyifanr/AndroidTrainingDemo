package cn.cfanr.complexlistviewdemo.model;

/**
 * Created by cfanr on 2015/12/4.
 */
public class MealShow{
    private int imgId;
    private String avatarId;
    private String userName;
    private String likeNum;
    private String commentNum;
    private String title;

    public int getImgId(){
        return imgId;
    }

    public void setImgId(int imgId){
        this.imgId=imgId;
    }

    public String getAvatarId(){
        return avatarId;
    }

    public void setAvatarId(String avatarId){
        this.avatarId=avatarId;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName=userName;
    }

    public String getLikeNum(){
        return likeNum;
    }

    public void setLikeNum(String likeNum){
        this.likeNum=likeNum;
    }

    public String getCommentNum(){
        return commentNum;
    }

    public void setCommentNum(String commentNum){
        this.commentNum=commentNum;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }
}
