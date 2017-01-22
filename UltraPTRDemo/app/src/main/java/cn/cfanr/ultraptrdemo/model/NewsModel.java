package cn.cfanr.ultraptrdemo.model;

/**
 * @author xifan
 * @time 2016/6/4
 * @desc gank上一条数据的model
 */
public class NewsModel {

    /**
     * _id : 57501d40421aa95655e4db8e
     * createdAt : 2016-06-02T19:49:20.594Z
     * desc : Android 小品：神奇的画笔之PorterDuff
     * publishedAt : 2016-06-03T11:42:44.370Z
     * source : web
     * type : Android
     * url : http://www.println.net/post/Android-PorterDuff
     * used : true
     * who : 潇涧
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}
