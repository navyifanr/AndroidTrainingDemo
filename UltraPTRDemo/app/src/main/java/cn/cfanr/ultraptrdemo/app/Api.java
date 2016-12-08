package cn.cfanr.ultraptrdemo.app;

/**
 * @author xifan
 * @time 2016/5/4
 * @desc
 */
public class Api {
    //api主机地址
    private static String host="http://news-at.zhihu.com/";

    /**
     * api启动页地址 http://news-at.zhihu.com/api/4/start-image/1080*1776
     * 320*432，480*728，720*1184，1080*1776
     */
    public static String url_launch=host+"api/4/start-image/";

    //最新消息
    public static String url_latest_news=host+"api/4/news/latest";

    //过往消息
    public static String url_before_news=host+"api/4/news/before/%s";

    //文章内容详情
    public static String url_article_content=host+"api/4/news/%s";

    //文章额外信息（评论和点赞数）
    public static String url_article_extra_data=host+"api/4/story-extra/%s";

    //文章评论列表的长评论
    public static String url_article_long_comments=host+"api/4/story/%s/long-comments";

    //文章评论列表的短评论
    public static String url_article_short_comments=host+"api/4/story/%s/short-comments";

    //主题列表
    public static String url_themes_list=host+"api/4/themes";

    //主题日报列表数据
    public static String url_theme_list_data=host+"api/4/theme/%s";

}
